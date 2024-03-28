package nemo.mestman.web.api.roadmap.service;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import nemo.mestman.AbstractContainerBaseTest;
import nemo.mestman.domain.member.entity.Member;
import nemo.mestman.domain.roadmap.dto.request.RoadMapRegisterRequest;
import nemo.mestman.domain.roadmap.dto.request.RoadMapUpdateRequest;
import nemo.mestman.domain.roadmap.dto.response.RoadMapDeleteResponse;
import nemo.mestman.domain.roadmap.dto.response.RoadMapListResponse;
import nemo.mestman.domain.roadmap.dto.response.RoadMapRegisterResponse;
import nemo.mestman.domain.roadmap.dto.response.RoadMapUpdateResponse;
import nemo.mestman.domain.roadmap.entity.RoadMap;
import nemo.mestman.domain.roadmap.service.RoadMapService;

class RoadMapServiceTest extends AbstractContainerBaseTest {

	@Autowired
	private RoadMapService service;

	@DisplayName("사용자는 로드맵을 등록한다")
	@Test
	void registerRoadMap() {
		// given
		RoadMapRegisterRequest request = RoadMapRegisterRequest.create("로드맵1", "히어로");
		// when
		RoadMapRegisterResponse response = service.registerRoadMap(request);
		// then
		assertAll(
			() -> assertThat(response.getName()).isEqualTo("로드맵1"),
			() -> assertThat(response.getCharacterClass()).isEqualTo("히어로"),
			() -> assertThat(roadMapRepository.findById(response.getId()).orElseThrow()).isNotNull()
		);
	}

	@DisplayName("사용자는 모든 로드맵을 조회한다")
	@Test
	void readAllRoadMap() {
		// given
		Member member = memberRepository.save(createMember());
		roadMapRepository.save(createRoadMap(member));
		// when
		RoadMapListResponse response = service.readAllRoadMap(member.getId());
		// then
		assertAll(
			() -> assertThat(response.getRoadMaps()).hasSize(1)
		);
	}

	@DisplayName("사용자는 로드맵을 수정한다")
	@Test
	void updateRoadMap() {
		// given
		Member member = memberRepository.save(createMember());
		roadMapRepository.save(createRoadMap(member));
		String name = "로드맵2";
		String characterClass = "팔라딘";
		RoadMapUpdateRequest request = RoadMapUpdateRequest.create(name, characterClass);
		// when
		RoadMapUpdateResponse response = service.updateRoadMap(member.getId(), request);
		// then
		RoadMap findRoadMap = roadMapRepository.findById(response.getId()).orElseThrow();
		assertAll(
			() -> assertThat(response.getName()).isEqualTo(name),
			() -> assertThat(response.getCharacterClass()).isEqualTo(characterClass),
			() -> assertThat(findRoadMap.getName()).isEqualTo(name),
			() -> assertThat(findRoadMap.getCharacterClass()).isEqualTo(characterClass)
		);
	}

	@DisplayName("사용자는 로드맵을 삭제한다")
	@Test
	void deleteRoadMap() {
		// given
		Member member = memberRepository.save(createMember());
		RoadMap roadMap = roadMapRepository.save(createRoadMap(member));
		// when
		RoadMapDeleteResponse response = service.deleteRoadMap(roadMap.getId());
		// then
		assertAll(
			() -> assertThat(response.getId()).isEqualTo(roadMap.getId()),
			() -> assertThat(roadMapRepository.findById(roadMap.getId())).isEmpty()
		);
	}
}
