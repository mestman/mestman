package nemo.mestman.domain.card.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import nemo.mestman.common.error.exception.MestmanException;
import nemo.mestman.domain.card.dto.request.CardRegisterRequest;
import nemo.mestman.domain.card.dto.request.CardUpdateRequest;
import nemo.mestman.domain.card.dto.response.CardDeleteResponse;
import nemo.mestman.domain.card.dto.response.CardItem;
import nemo.mestman.domain.card.dto.response.CardListResponse;
import nemo.mestman.domain.card.dto.response.CardRegisterResponse;
import nemo.mestman.domain.card.dto.response.CardUpdateResponse;
import nemo.mestman.domain.card.entity.Card;
import nemo.mestman.domain.card.errorcode.CardErrorCode;
import nemo.mestman.domain.card.repository.CardRepository;
import nemo.mestman.domain.roadmap.entity.RoadMap;
import nemo.mestman.domain.roadmap.service.RoadMapService;

@Service
@RequiredArgsConstructor
public class CardService {

	private final CardRepository repository;
	private final RoadMapService roadMapService;

	@Transactional
	public CardRegisterResponse registerCard(CardRegisterRequest request) {
		RoadMap roadMap = roadMapService.readOneRoadMap(request.getRoadMapId());
		Card card = repository.save(request.toEntity(roadMap));
		return CardRegisterResponse.from(card);
	}

	@Transactional(readOnly = true)
	public CardListResponse readAllCards(Long roadMapId) {
		RoadMap roadMap = roadMapService.readOneRoadMap(roadMapId);
		List<CardItem> cards = roadMap.getCards().stream()
			.map(CardItem::from)
			.collect(Collectors.toList());
		return CardListResponse.create(cards);
	}

	@Transactional(readOnly = true)
	public Card readOneCard(Long cardId) {
		return repository.findById(cardId)
			.orElseThrow(() -> new MestmanException(CardErrorCode.NOT_FOUND));
	}

	@Transactional
	public CardUpdateResponse updateCard(Long cardId, CardUpdateRequest request) {
		Card card = readOneCard(cardId);
		RoadMap roadMap = roadMapService.readOneRoadMap(request.getRoadMapId());
		card.change(request.toEntity(roadMap));
		return CardUpdateResponse.from(card);
	}

	@Transactional
	public CardDeleteResponse deleteCard(Long cardId) {
		repository.deleteById(cardId);
		return CardDeleteResponse.from(cardId);
	}

	@Transactional
	public void concateCards() {
	}
}
