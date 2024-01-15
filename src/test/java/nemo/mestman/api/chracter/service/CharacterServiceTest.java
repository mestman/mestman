package nemo.mestman.api.chracter.service;

import static org.assertj.core.api.Assertions.*;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

import io.netty.handler.codec.http.HttpHeaderValues;
import lombok.extern.slf4j.Slf4j;
import nemo.mestman.domain.symbol.SymbolDetail;
import nemo.mestman.util.ObjectMapperUtil;
import nemo.mestman.web.api.chracter.controller.response.SymbolMinDays;
import nemo.mestman.web.api.chracter.controller.response.SymbolMinDaysResponse;
import nemo.mestman.web.api.chracter.service.CharacterService;
import nemo.mestman.web.api.chracter.service.request.SymbolMinDaysServiceRequest;
import nemo.mestman.web.api.chracter.service.response.maple.OCIDResponse;
import nemo.mestman.web.api.chracter.service.response.maple.SymbolEquipmentResponse;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import reactor.core.publisher.Mono;

@Slf4j
@SpringBootTest
class CharacterServiceTest {
	private static MockWebServer mockWebServer;

	private CharacterService characterService;

	@Value("${maple.api-key}")
	private String apiKey;

	@BeforeAll
	static void setUp() throws IOException {
		mockWebServer = new MockWebServer();
		mockWebServer.start();
	}

	@BeforeEach
	void initialize() {
		String baseUrl = String.format("http://localhost:%s", mockWebServer.getPort());
		WebClient webClient = WebClient.builder().baseUrl(baseUrl)
			.defaultHeaders(header -> {
				header.add("x-nxopen-api-key", apiKey);
				header.add("accept", HttpHeaderValues.APPLICATION_JSON.toString());
			})
			.build();
		characterService = new CharacterService(webClient);
	}

	@AfterAll
	static void tearDown() throws IOException {
		mockWebServer.shutdown();
	}

	@DisplayName("사용자는 캐릭터의 장착 심볼에 대한 만렙 최소일을 계산한다")
	@Test
	void calcSymbolMinimumDays() {
		// given
		SymbolMinDaysServiceRequest request = createSymbolEquipmentServiceRequest();

		enqueue(HttpStatus.OK.value(), createOICDResponse());

		SymbolEquipmentResponse symbolEquipmentResponse = createSymbolEquipmentResponse(LocalDate.of(2024, 1, 14),
			SymbolDetail.Name.ACANE1, 20, 0);
		enqueue(HttpStatus.OK.value(), symbolEquipmentResponse);

		// when
		Mono<SymbolMinDaysResponse> mono = characterService.calcSymbolMinimumDays(request);

		// then
		SymbolMinDaysResponse expected = SymbolMinDaysResponse.builder()
			.date(LocalDate.of(2024, 1, 14))
			.symbol(List.of(
					SymbolMinDays.of(
						createSymbolDetail(SymbolDetail.Name.ACANE1, 20, 0),
						0,
						LocalDate.of(2024, 1, 14))
				)
			)
			.build();
		assertThat(mono.block()).isEqualTo(expected);
	}

	@DisplayName("사용자는 캐릭터의 장착 심볼이 특정 레벨에 해당하는 요구치를 넘어간 상태인 경우에도 최소일수를 계산한다")
	@Test
	void calcSymbolMinimumDays_givenOverSymbolGrowthCount_thenOk() {
		// given
		SymbolMinDaysServiceRequest request = createSymbolEquipmentServiceRequest();

		enqueue(HttpStatus.OK.value(), createOICDResponse());

		SymbolEquipmentResponse symbolEquipmentResponse = createSymbolEquipmentResponse(LocalDate.of(2024, 1, 14),
			SymbolDetail.Name.AUTHENTIC1, 8, 810);
		enqueue(HttpStatus.OK.value(), symbolEquipmentResponse);

		// when
		Mono<SymbolMinDaysResponse> mono = characterService.calcSymbolMinimumDays(request);

		// then
		SymbolMinDaysResponse expected = SymbolMinDaysResponse.builder()
			.date(LocalDate.of(2024, 1, 14))
			.symbol(List.of(
					SymbolMinDays.of(
						createSymbolDetail(SymbolDetail.Name.AUTHENTIC1, 8, 810),
						1935,
						LocalDate.of(2024, 4, 19))
				)
			)
			.build();
		assertThat(mono.block()).isEqualTo(expected);
	}

	@DisplayName("사용자는 없는 캐릭터의 장착 심볼을 조회할 수 없다")
	@Test
	void givenNonExistName_whenReadSymbolEquipment_thenNotFoundError() {
		// given
		SymbolMinDaysServiceRequest request = createSymbolEquipmentServiceRequestForNonExistName();
		// when
		Throwable throwable = catchThrowable(() -> characterService.calcSymbolMinimumDays(request));
		// then
		assertThat(throwable)
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage("존재하지 않는 이름입니다. characterName=무");
	}

	private SymbolEquipmentResponse createSymbolEquipmentResponse(LocalDate localDate, SymbolDetail.Name name,
		int level, int growthCount) {
		return SymbolEquipmentResponse.builder()
			.date(createLocalDate(localDate))
			.characterClass("제로")
			.symbol(List.of(createSymbolDetail(name, level, growthCount)))
			.build();
	}

	@NotNull
	private static String createLocalDate(LocalDate localDate) {
		return localDate.atTime(LocalTime.of(9, 0)).format(DateTimeFormatter.ISO_DATE_TIME);
	}

	private OCIDResponse createOICDResponse() {
		return OCIDResponse.builder()
			.ocid(createOICD())
			.build();
	}

	private String createOICD() {
		return "3334673895e477243ae0ead79cb223d3efe8d04e6d233bd35cf2fabdeb93fb0d";
	}

	private void enqueue(int statusCode, Object data) {
		mockWebServer.enqueue(new MockResponse().setResponseCode(statusCode)
			.setBody(ObjectMapperUtil.serialize(data))
			.addHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON));
	}

	private SymbolMinDaysServiceRequest createSymbolEquipmentServiceRequest() {
		return SymbolMinDaysServiceRequest.builder()
			.characterName("제빛제로")
			.build();
	}

	private SymbolMinDaysServiceRequest createSymbolEquipmentServiceRequestForNonExistName() {
		return SymbolMinDaysServiceRequest.builder()
			.characterName("무")
			.build();
	}

	private SymbolDetail createSymbolDetail(SymbolDetail.Name name, int level, int growthCount) {
		return SymbolDetail.builder()
			.symbolName(name)
			.symbolLevel(level)
			.symbolGrowthCount(growthCount).build();
	}

}
