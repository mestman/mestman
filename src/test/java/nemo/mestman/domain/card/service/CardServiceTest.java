package nemo.mestman.domain.card.service;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Objects;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import nemo.mestman.AbstractContainerBaseTest;
import nemo.mestman.domain.card.dto.request.CardRegisterRequest;
import nemo.mestman.domain.card.dto.response.CardListResponse;
import nemo.mestman.domain.card.dto.response.CardRegisterResponse;
import nemo.mestman.domain.member.entity.Member;
import nemo.mestman.domain.roadmap.entity.RoadMap;

class CardServiceTest extends AbstractContainerBaseTest {

	@Autowired
	private CardService service;

	@DisplayName("사용자는 카드를 등록합니다")
	@Test
	void registerCard() {
		// given
		Member member = memberRepository.save(createMember());
		RoadMap roadMap = roadMapRepository.save(createRoadMap(member));
		String title = "초보자용";
		Long roadMapId = roadMap.getId();
		CardRegisterRequest request = CardRegisterRequest.create(title, roadMapId);
		// when
		CardRegisterResponse response = service.registerCard(request);
		// then
		assertAll(
			() -> assertThat(response.getId()).isNotNull(),
			() -> assertThat(response.getTitle()).isEqualTo(title),
			() -> assertThat(
				cardRepository.findById(Objects.requireNonNull(response.getId())).orElseThrow()).isNotNull()
		);
	}

	@DisplayName("사용자는 특정 로드맵의 카드들을 조회합니다")
	@Test
	void readAllCards() {
		// given
		Member member = memberRepository.save(createMember());
		RoadMap roadMap = roadMapRepository.save(createRoadMap(member));
		cardRepository.save(createCard(roadMap));
		// when
		CardListResponse response = service.readAllCards(roadMap.getId());
		// then
		assertAll(
			() -> assertThat(response.getCards())
				.hasSize(1)
				.extracting("title")
				.containsExactlyInAnyOrder("초보자용")
		);
	}
}
