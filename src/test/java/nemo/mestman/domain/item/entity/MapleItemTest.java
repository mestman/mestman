package nemo.mestman.domain.item.entity;

import static nemo.mestman.domain.item.entity.ItemOption.OptionType.*;

import java.util.List;
import java.util.stream.Stream;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import nemo.mestman.AbstractContainerBaseTest;
import nemo.mestman.domain.card.entity.Card;
import nemo.mestman.domain.member.entity.Member;
import nemo.mestman.domain.roadmap.entity.RoadMap;

class MapleItemTest extends AbstractContainerBaseTest {
	@DisplayName("아이템 옵션들을 파싱한다")
	@Test
	void parseItemOptions() {
		// given
		Member member = createMember();
		RoadMap roadMap = createRoadMap(member);
		Card card = createCard(roadMap);
		MapleItem mapleItem = createMapleItem(card);

		mapleItem.addOption(ItemOption.byType(STR, 10));

		// when
		String result = mapleItem.parseItemOptions();

		// then
		Assertions.assertThat(result).isEqualTo("STR=10");
	}

	@DisplayName("아이템의 스타포스 옵션을 계산한다")
	@MethodSource(value = "levelAndStarForce")
	@ParameterizedTest
	void calStartForce(int level, int starForce, List<String> expects) {
		// given
		String name = "하이네스 워리어헬름";
		MapleItem item = createMapleItem(name, level);
		item.increaseStarForce(starForce);
		// when
		List<ItemOption> options = item.calStartForce();
		// then
		Assertions.assertThat(options)
			.hasSize(4)
			.map(ItemOption::parse)
			.containsExactlyInAnyOrderElementsOf(expects);
	}

	private static Stream<Arguments> levelAndStarForce() {
		return Stream.of(
			Arguments.arguments(130, 18, List.of("STR=61", "DEX=61", "HP=255", "ATT=24")),
			Arguments.arguments(140, 18, List.of("STR=67", "DEX=67", "HP=255", "ATT=27")),
			Arguments.arguments(150, 18, List.of("STR=73", "DEX=73", "HP=255", "ATT=30"))
		);
	}

}
