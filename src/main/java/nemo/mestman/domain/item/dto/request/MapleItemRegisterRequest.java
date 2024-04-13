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

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
public class MapleItemRegisterRequest {
	@NotBlank
	private String name;
	private Integer level;
	@PositiveOrZero
	private Integer starForce;
	@NotNull
	private Long cardId;

	public static MapleItemRegisterRequest create(String name, Integer level, Integer starForce, Long cardId) {
		return new MapleItemRegisterRequest(name, level, starForce, cardId);
	}

	public MapleItem toEntity(Card card) {
		return MapleItem.card(
			name,
			level,
			card
		);
	}
}
