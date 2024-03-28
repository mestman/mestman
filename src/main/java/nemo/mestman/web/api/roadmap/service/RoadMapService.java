package nemo.mestman.web.api.roadmap.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import nemo.mestman.domain.roadmap.RoadMap;
import nemo.mestman.domain.roadmap.RoadMapRepository;
import nemo.mestman.web.api.roadmap.request.RoadMapRegisterRequest;
import nemo.mestman.web.api.roadmap.request.RoadMapUpdateRequest;
import nemo.mestman.web.api.roadmap.response.RoadMapDeleteResponse;
import nemo.mestman.web.api.roadmap.response.RoadMapItem;
import nemo.mestman.web.api.roadmap.response.RoadMapListResponse;
import nemo.mestman.web.api.roadmap.response.RoadMapRegisterResponse;
import nemo.mestman.web.api.roadmap.response.RoadMapUpdateResponse;

@Service
@RequiredArgsConstructor
public class RoadMapService {

	private final RoadMapRepository roadMapRepository;

	@Transactional
	public RoadMapRegisterResponse registerRoadMap(RoadMapRegisterRequest request) {
		RoadMap roadMap = roadMapRepository.save(request.toEntity());
		return RoadMapRegisterResponse.from(roadMap);
	}

	@Transactional(readOnly = true)
	public RoadMapListResponse readAllRoadMap(Long memberId) {
		List<RoadMap> roadMaps = roadMapRepository.findAllByMemberId(memberId);
		return RoadMapListResponse.from(
			roadMaps.stream()
				.map(RoadMapItem::from)
				.collect(Collectors.toList())
		);
	}

	@Transactional
	public RoadMapUpdateResponse updateRoadMap(Long roadmapId, RoadMapUpdateRequest request) {
		RoadMap roadMap = roadMapRepository.findById(roadmapId)
			.orElseThrow(() -> new IllegalArgumentException("해당 로드맵을 찾을 수 없습니다"));
		roadMap.change(
			request.getName(),
			request.getCharacterClass()
		);
		return RoadMapUpdateResponse.from(roadMap);
	}

	@Transactional
	public RoadMapDeleteResponse deleteRoadMap(Long roadmapId) {
		roadMapRepository.deleteById(roadmapId);
		return RoadMapDeleteResponse.from(roadmapId);
	}
}
