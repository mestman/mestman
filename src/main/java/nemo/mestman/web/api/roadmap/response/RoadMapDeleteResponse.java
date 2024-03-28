package nemo.mestman.web.api.roadmap.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class RoadMapDeleteResponse {
	private Long id;

	public static RoadMapDeleteResponse from(Long roadmapId) {
		return new RoadMapDeleteResponse(roadmapId);
	}
}
