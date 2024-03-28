package nemo.mestman.domain.roadmap.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import nemo.mestman.common.api.ApiResponse;
import nemo.mestman.domain.roadmap.request.RoadMapUpdateRequest;
import nemo.mestman.domain.roadmap.response.RoadMapDeleteResponse;
import nemo.mestman.domain.roadmap.response.RoadMapListResponse;
import nemo.mestman.domain.roadmap.response.RoadMapUpdateResponse;
import nemo.mestman.common.success.RoadMapSuccessCode;
import nemo.mestman.domain.roadmap.request.RoadMapRegisterRequest;
import nemo.mestman.domain.roadmap.response.RoadMapRegisterResponse;
import nemo.mestman.domain.roadmap.service.RoadMapService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/roadmaps")
public class RoadMapRestController {

	private final RoadMapService service;

	@PostMapping
	public ResponseEntity<ApiResponse<RoadMapRegisterResponse>> registerRoadMap(
		@Valid @RequestBody RoadMapRegisterRequest request) {
		return ResponseEntity.ok(
			ApiResponse.success(
				RoadMapSuccessCode.OK_REGISTER_ROADMAP,
				service.registerRoadMap(request)
			)
		);
	}

	@GetMapping
	public ResponseEntity<ApiResponse<RoadMapListResponse>> readAllRoadMap(Long memberId) {
		return ResponseEntity.ok(
			ApiResponse.success(
				RoadMapSuccessCode.OK_READ_ROADMAPS,
				service.readAllRoadMap(memberId)
			)
		);
	}

	@PutMapping("/{roadmapId}")
	public ResponseEntity<ApiResponse<RoadMapUpdateResponse>> updateRoadMap(
		@PathVariable Long roadmapId,
		@Valid @RequestBody RoadMapUpdateRequest request) {
		return ResponseEntity.ok(
			ApiResponse.success(
				RoadMapSuccessCode.OK_UPDATE_ROADMAP,
				service.updateRoadMap(roadmapId, request)
			)
		);
	}

	@DeleteMapping("/{roadmapId}")
	public ResponseEntity<ApiResponse<RoadMapDeleteResponse>> deleteRoadMap(
		@PathVariable Long roadmapId) {
		return ResponseEntity.ok(
			ApiResponse.success(
				RoadMapSuccessCode.OK_DELETE_ROADMAP,
				service.deleteRoadMap(roadmapId)
			)
		);
	}
}
