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
public class CardRegisterResponse {

	private Long id;
	private String title;

	public static CardRegisterResponse from(Card card) {
		return new CardRegisterResponse(
			card.getId(),
			card.getTitle()
		);
	}
}
