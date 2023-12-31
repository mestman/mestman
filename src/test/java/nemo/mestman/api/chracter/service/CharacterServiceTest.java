package nemo.mestman.api.chracter.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.slf4j.Slf4j;
import nemo.mestman.web.api.chracter.controller.response.CharacterSymbolMinimumDaysResponse;
import nemo.mestman.web.api.chracter.service.CharacterService;
import nemo.mestman.web.api.chracter.service.request.CharacterSymbolEquipmentServiceRequest;

@Slf4j
@SpringBootTest
class CharacterServiceTest {

	@Autowired
	private CharacterService characterService;

	// TODO: WebClient 모킹
	@DisplayName("사용자는 캐릭터의 장착 심볼을 조회한다")
	@Test
	void readSymbolEquipment() {
		// given
		CharacterSymbolEquipmentServiceRequest request = createSymbolEquipmentServiceRequest();
		// when
		CharacterSymbolMinimumDaysResponse response = characterService.readSymbolMinimumDays(request);
		// then
		Assertions.assertThat(response).isNotNull();
	}

	private static CharacterSymbolEquipmentServiceRequest createSymbolEquipmentServiceRequest() {
		return CharacterSymbolEquipmentServiceRequest.builder()
			.characterName("제빛제로")
			.build();
	}

}
