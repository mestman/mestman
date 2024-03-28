package nemo.mestman.domain.card.dto.response;

import java.util.List;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
public class CardListResponse {
	private List<CardItem> cards;

	public static CardListResponse create(List<CardItem> cards) {
		return new CardListResponse(cards);
	}
}
