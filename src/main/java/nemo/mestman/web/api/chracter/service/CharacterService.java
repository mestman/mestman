package nemo.mestman.web.api.chracter.service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.function.Function;

import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;

import lombok.extern.slf4j.Slf4j;
import nemo.mestman.web.api.chracter.controller.response.SymbolMinDaysResponse;
import nemo.mestman.web.api.chracter.service.request.SymbolMinDaysServiceRequest;
import nemo.mestman.web.api.chracter.service.response.maple.OCIDResponse;
import nemo.mestman.web.api.chracter.service.response.maple.SymbolEquipmentResponse;
import nemo.mestman.web.error.maple.MapleError;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Slf4j
@Service
public class CharacterService {

	private static final Duration TIMEOUT = Duration.ofSeconds(10L);

	private final WebClient client;

	public CharacterService(WebClient client) {
		this.client = client;
	}

	public Mono<SymbolMinDaysResponse> calcSymbolMinimumDays(
		SymbolMinDaysServiceRequest request) {
		// 캐릭터 식별자 조회 후 장착 심볼 조회
		return fetchOCID(request.getCharacterName())
			.map(OCIDResponse::getOcid)
			.flatMap(this::fetchSymbolEquipment)
			.map(response -> response.toSymbolMinDaysResponse());
	}

	private Mono<OCIDResponse> fetchOCID(String characterName) {
		return client.get()
			.uri(uriBuilder -> uriBuilder
				.path("/id")
				.queryParam("character_name", characterName)
				.build())
			.retrieve()
			.onStatus(HttpStatusCode::isError, handleError(characterName))
			.bodyToMono(OCIDResponse.class)
			.timeout(TIMEOUT);
	}

	private Function<ClientResponse, Mono<? extends Throwable>> handleError(String input) {
		return response -> {
			logTraceResponse(response);
			return response.bodyToMono(MapleError.class)
				.timeout(TIMEOUT)
				.switchIfEmpty(Mono.defer(() -> Mono.just(MapleError.internalServerError())))
				.flatMap(mapleError -> Mono.error(mapleError.createException(input)));
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
			.onStatus(HttpStatusCode::isError, handleError(ocid))
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
