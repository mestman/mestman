package nemo.mestman.domain.item.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import nemo.mestman.domain.card.entity.Card;
import nemo.mestman.domain.card.service.CardService;
import nemo.mestman.domain.item.dto.request.MapleItemRegisterRequest;
import nemo.mestman.domain.item.dto.response.MapleItemRegisterResponse;
import nemo.mestman.domain.item.entity.MapleItem;
import nemo.mestman.domain.item.repository.MapleItemRepository;

@Service
@RequiredArgsConstructor
public class MapleItemService {

	private final MapleItemRepository repository;
	private final CardService cardService;

	public MapleItemRegisterResponse registerItem(MapleItemRegisterRequest request) {
		Card card = cardService.readOneCard(request.getCardId());
		MapleItem item = repository.save(request.toEntity(card));
		return MapleItemRegisterResponse.from(item);
	}
}
