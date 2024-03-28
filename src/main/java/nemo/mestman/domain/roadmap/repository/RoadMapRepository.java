package nemo.mestman.domain.roadmap.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import nemo.mestman.domain.roadmap.entity.RoadMap;

public interface RoadMapRepository extends JpaRepository<RoadMap, Long> {

	@Query("select r from RoadMap r where r.member.id = :memberId")
	List<RoadMap> findAllByMemberId(@Param("memberId") Long memberId);
}
