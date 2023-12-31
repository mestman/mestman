package nemo.mestman.web.api.chracter.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nemo.mestman.web.api.chracter.controller.request.CharacterSymbolEquipmentRequest;
import nemo.mestman.web.api.chracter.controller.response.CharacterSymbolMinimumDaysResponse;
import nemo.mestman.web.api.chracter.service.CharacterService;

@Slf4j
@RequestMapping("/api/character")
@RequiredArgsConstructor
@RestController
public class CharacterRestController {

	private final CharacterService characterService;

	// 심볼 만렙 최소일수
	@GetMapping("/symbol")
	public CharacterSymbolMinimumDaysResponse readSymbolMinimumDays(
		@RequestBody CharacterSymbolEquipmentRequest request) {
		CharacterSymbolMinimumDaysResponse response = characterService.readSymbolMinimumDays(
			request.toServiceRequest());
		log.info("response : {}", response);
		return response;
	}
}
