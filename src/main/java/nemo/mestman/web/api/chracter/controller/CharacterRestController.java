package nemo.mestman.web.api.chracter.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nemo.mestman.web.api.chracter.controller.request.CharacterSymbolEquipmentRequest;
import nemo.mestman.web.api.chracter.controller.response.SymbolMinDaysResponse;
import nemo.mestman.web.api.chracter.service.CharacterService;
import reactor.core.publisher.Mono;

@Slf4j
@RequestMapping("/api/character")
@RequiredArgsConstructor
@RestController
public class CharacterRestController {

	private final CharacterService characterService;

	// 심볼 만렙 최소일수
	@GetMapping("/symbol")
	public Mono<ResponseEntity<SymbolMinDaysResponse>> readSymbolMinimumDays(
		@Valid CharacterSymbolEquipmentRequest request) {
		return characterService.calcSymbolMinimumDays(request.toServiceRequest())
			.map(ResponseEntity::ok);
	}

	@ExceptionHandler(value = {IllegalArgumentException.class})
	public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException e) {
		return ResponseEntity.badRequest()
			.body(e.getMessage());
	}
}
