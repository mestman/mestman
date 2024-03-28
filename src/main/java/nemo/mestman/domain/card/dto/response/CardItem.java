package nemo.mestman.domain.card.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import nemo.mestman.domain.card.entity.Card;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
public class CardItem {

	private Long id;
	private String title;
	private Long roadMapId;

	public static CardItem from(Card card) {
		return new CardItem(
			card.getId(),
			card.getTitle(),
			card.getRoadMap().getId()
		);
	}
}
