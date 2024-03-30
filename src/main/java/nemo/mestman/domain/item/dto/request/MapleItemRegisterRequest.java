package nemo.mestman.domain.item.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import nemo.mestman.domain.card.entity.Card;
import nemo.mestman.domain.item.entity.MapleItem;
import nemo.mestman.domain.item.entity.StarForce;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
public class MapleItemRegisterRequest {
	@NotBlank
	private String name;
	@PositiveOrZero
	private Integer starForce;
	@NotNull
	private Long cardId;

	public static MapleItemRegisterRequest create(String name, Integer starForce, Long cardId) {
		return new MapleItemRegisterRequest(name, starForce, cardId);
	}

	public MapleItem toEntity(Card card) {
		return MapleItem.create(
			name,
			StarForce.create(starForce),
			card
		);
	}
}
