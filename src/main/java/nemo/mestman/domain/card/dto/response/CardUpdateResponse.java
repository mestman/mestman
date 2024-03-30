package nemo.mestman.domain.card.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import nemo.mestman.domain.card.entity.Card;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
public class CardUpdateResponse {

	private Long id;
	private String title;

	public static CardUpdateResponse from(Card card) {
		return new CardUpdateResponse(card.getId(), card.getTitle());
	}
}
