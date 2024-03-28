package nemo.mestman.domain.roadmap;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RoadMapRepository extends JpaRepository<RoadMap, Long> {

	@Query("select r from RoadMap r where r.member.id = :memberId")
	List<RoadMap> findAllByMemberId(@Param("memberId") Long memberId);
}
