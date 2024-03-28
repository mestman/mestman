package nemo.mestman.domain.card.dto.request;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import nemo.mestman.domain.card.entity.Card;
import nemo.mestman.domain.roadmap.entity.RoadMap;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
public class CardRegisterRequest {
	private String title;
	private Long roadMapId;

	public static CardRegisterRequest create(String title, Long roadMapId) {
		return new CardRegisterRequest(title, roadMapId);
	}

	public Card toEntity(RoadMap roadMap) {
		return Card.create(title, roadMap);
	}
}
