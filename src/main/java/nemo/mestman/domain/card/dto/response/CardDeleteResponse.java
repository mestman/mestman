package nemo.mestman.domain.card.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
public class CardDeleteResponse {

	private Long id;

	public static CardDeleteResponse from(Long cardId) {
		return new CardDeleteResponse(cardId);
	}
}
