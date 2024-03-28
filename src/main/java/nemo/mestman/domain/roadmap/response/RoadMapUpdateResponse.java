package nemo.mestman.domain.roadmap.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import nemo.mestman.domain.roadmap.entity.RoadMap;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class RoadMapUpdateResponse {
	private Long id;
	private String name;
	private String characterClass;

	public static RoadMapUpdateResponse from(RoadMap roadMap) {
		return RoadMapUpdateResponse.builder()
			.id(roadMap.getId())
			.name(roadMap.getName())
			.characterClass(roadMap.getCharacterClass())
			.build();
	}
}
