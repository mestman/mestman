package nemo.mestman.web.api.chracter.service;

import static io.netty.handler.codec.http.HttpHeaders.Values.*;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;

import lombok.extern.slf4j.Slf4j;
import nemo.mestman.web.api.chracter.controller.response.CharacterSymbolMinimumDaysResponse;
import nemo.mestman.web.api.chracter.service.request.CharacterSymbolEquipmentServiceRequest;
import nemo.mestman.web.api.chracter.service.response.maple.OCIDResponse;
import nemo.mestman.web.api.chracter.service.response.maple.SymbolEquipmentResponse;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Slf4j
@Service
public class CharacterService {

	private static final String BASE_URL = "https://open.api.nexon.com/maplestory/v1";
	private static final Duration TIMEOUT = Duration.ofSeconds(10L);

	private final WebClient client;

	public CharacterService(
		@Value("${maple.api-key}") String apiKey,
		WebClient.Builder webClientBuilder) {
		this.client = webClientBuilder.baseUrl(BASE_URL)
			.defaultHeaders(header -> {
				header.add("x-nxopen-api-key", apiKey);
				header.add("accept", APPLICATION_JSON);
			})
			.build();
	}

	public CharacterSymbolMinimumDaysResponse readSymbolMinimumDays(
		CharacterSymbolEquipmentServiceRequest request) {
		// 캐릭터 식별자 조회 후 장착 심볼 조회
		SymbolEquipmentResponse symbolEquipmentResponse = fetchOCID(request.getCharacterName())
			.map(OCIDResponse::getOcid)
			.flatMap(this::fetchSymbolEquipment)
			.blockOptional(TIMEOUT)
			.orElseThrow(() -> new IllegalStateException("심볼 정보를 가져오지 못했습니다"));
		return symbolEquipmentResponse.createCharacterSymbolMinimumDaysResponse();
	}

	private Mono<OCIDResponse> fetchOCID(String characterName) {
		return client.get()
			.uri(uriBuilder -> uriBuilder
				.path("/id")
				.queryParam("character_name", characterName)
				.build())
			.retrieve()
			.onStatus(HttpStatusCode::isError, handleErrorResponse(characterName))
			.bodyToMono(OCIDResponse.class)
			.timeout(TIMEOUT);
	}

	private Function<ClientResponse, Mono<? extends Throwable>> handleErrorResponse(String input) {
		return response -> {
			logTraceResponse(response);
			return Mono.error(new IllegalStateException(String.format("Failed! %s", input)));
		};
	}

	private Mono<SymbolEquipmentResponse> fetchSymbolEquipment(String ocid) {
		String date = LocalDateTime.now().minusDays(1).toLocalDate().toString();
		return client.get()
			.uri(uriBuilder -> uriBuilder
				.path("/character/symbol-equipment")
				.queryParam("ocid", ocid)
				.queryParam("date", date)
				.build())
			.retrieve()
			.onStatus(HttpStatusCode::isError, handleErrorResponse(ocid))
			.bodyToMono(SymbolEquipmentResponse.class)
			.timeout(TIMEOUT);
	}

	private void logTraceResponse(ClientResponse response) {
		if (log.isTraceEnabled()) {
			log.trace("Response status: {}", response.statusCode());
			log.trace("Response headers: {}", response.headers().asHttpHeaders());
			response.bodyToMono(String.class)
				.publishOn(Schedulers.boundedElastic())
				.subscribe(body -> log.trace("Response body: {}", body));
		}
	}
}
