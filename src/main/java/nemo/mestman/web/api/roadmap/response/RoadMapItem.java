package nemo.mestman.web.api.roadmap.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import nemo.mestman.domain.roadmap.RoadMap;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class RoadMapItem {
	private Long id;
	private String name;
	private String characterClass;

	public static RoadMapItem from(RoadMap roadMap) {
		return RoadMapItem.builder()
			.id(roadMap.getId())
			.name(roadMap.getName())
			.characterClass(roadMap.getCharacterClass())
			.build();
	}
}
