package nemo.mestman.domain.item.service;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import nemo.mestman.AbstractContainerBaseTest;
import nemo.mestman.domain.card.entity.Card;
import nemo.mestman.domain.item.dto.request.MapleItemRegisterRequest;
import nemo.mestman.domain.item.dto.response.MapleItemRegisterResponse;
import nemo.mestman.domain.member.entity.Member;
import nemo.mestman.domain.roadmap.entity.RoadMap;

class MapleItemServiceTest extends AbstractContainerBaseTest {

	@Autowired
	private MapleItemService service;

	@DisplayName("카드에 장비를 등록한다")
	@Test
	void registerItem() {
		// given
		Member member = memberRepository.save(createMember());
		RoadMap roadMap = roadMapRepository.save(createRoadMap(member));
		Card card = cardRepository.save(createCard(roadMap));

		String name = "하이네스 워리어헬름";
		int level = 150;
		int startForce = 18;
		Long cardId = card.getId();
		MapleItemRegisterRequest request = MapleItemRegisterRequest.create(name, level, startForce, cardId);

		// when
		MapleItemRegisterResponse response = service.registerItem(request);

		// then
		assertAll(
			() -> assertThat(response.getName()).isEqualTo(name),
			() -> assertThat(response.getStarForce()).isEqualTo(startForce)
		);
	}

}
