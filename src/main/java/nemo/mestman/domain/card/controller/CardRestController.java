package nemo.mestman.domain.card.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import nemo.mestman.common.api.ApiResponse;
import nemo.mestman.domain.card.dto.request.CardRegisterRequest;
import nemo.mestman.domain.card.dto.request.CardUpdateRequest;
import nemo.mestman.domain.card.dto.response.CardDeleteResponse;
import nemo.mestman.domain.card.dto.response.CardListResponse;
import nemo.mestman.domain.card.dto.response.CardRegisterResponse;
import nemo.mestman.domain.card.dto.response.CardUpdateResponse;
import nemo.mestman.domain.card.service.CardService;
import nemo.mestman.domain.card.successcode.CardSuccessCode;

@RestController
@RequestMapping("/api/cards")
@RequiredArgsConstructor
public class CardRestController {

	private final CardService service;

	@PostMapping
	public ResponseEntity<ApiResponse<CardRegisterResponse>> registerCard(
		@Valid @RequestBody CardRegisterRequest request) {
		CardRegisterResponse response = service.registerCard(request);
		return ResponseEntity.ok(ApiResponse.from(CardSuccessCode.REGISTER_CARD, response));
	}

	@GetMapping
	public ResponseEntity<ApiResponse<CardListResponse>> readAllCards(Long memberId) {
		CardListResponse response = service.readAllCards(memberId);
		return ResponseEntity.ok(ApiResponse.from(CardSuccessCode.READ_CARD, response));
	}

	@PutMapping("/{cardId}")
	public ResponseEntity<ApiResponse<CardUpdateResponse>> updateCard(
		@PathVariable Long cardId,
		@Valid @RequestBody CardUpdateRequest request) {
		CardUpdateResponse response = service.updateCard(cardId, request);
		return ResponseEntity.ok(ApiResponse.from(CardSuccessCode.UPDATE_CARD, response));
	}

	@DeleteMapping("/{cardId}")
	public ResponseEntity<ApiResponse<CardDeleteResponse>> deleteCard(@PathVariable Long cardId) {
		CardDeleteResponse response = service.deleteCard(cardId);
		return ResponseEntity.ok(ApiResponse.from(CardSuccessCode.DELETE_CARD, response));
	}

	@PostMapping("/concate")
	public ResponseEntity<ApiResponse<Void>> concateCards() {
		service.concateCards();
		return ResponseEntity.ok(ApiResponse.from(CardSuccessCode.CONCAT_CARDS));
	}
}
