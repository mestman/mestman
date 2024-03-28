package nemo.mestman.domain.roadmap.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import nemo.mestman.common.error.exception.MestmanException;
import nemo.mestman.domain.roadmap.dto.request.RoadMapRegisterRequest;
import nemo.mestman.domain.roadmap.dto.request.RoadMapUpdateRequest;
import nemo.mestman.domain.roadmap.dto.response.RoadMapDeleteResponse;
import nemo.mestman.domain.roadmap.dto.response.RoadMapItem;
import nemo.mestman.domain.roadmap.dto.response.RoadMapListResponse;
import nemo.mestman.domain.roadmap.dto.response.RoadMapRegisterResponse;
import nemo.mestman.domain.roadmap.dto.response.RoadMapUpdateResponse;
import nemo.mestman.domain.roadmap.entity.RoadMap;
import nemo.mestman.domain.roadmap.errorcode.RoadMapErrorCode;
import nemo.mestman.domain.roadmap.repository.RoadMapRepository;

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

	@Transactional(readOnly = true)
	public RoadMap readOneRoadMap(Long roadMapId) {
		return roadMapRepository.findByRoadMapId(roadMapId)
			.orElseThrow(() -> new MestmanException(RoadMapErrorCode.NOT_FOUND));
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
