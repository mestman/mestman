package nemo.mestman.domain.item.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import nemo.mestman.domain.item.entity.MapleItem;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
public class MapleItemRegisterResponse {
	private Long id;
	private String name;
	private int starForce;

	public static MapleItemRegisterResponse from(MapleItem item) {
		return new MapleItemRegisterResponse(
			item.getId(),
			item.getName(),
			item.getStarForce().getValue());
	}
}
