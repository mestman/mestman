package nemo.mestman.domain.roadmap.response;

import java.util.List;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class RoadMapListResponse {
	private List<RoadMapItem> roadMaps;

	public static RoadMapListResponse from(List<RoadMapItem> roadMaps) {
		return new RoadMapListResponse(roadMaps);
	}
}
