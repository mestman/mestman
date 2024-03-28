package nemo.mestman.domain.member.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import nemo.mestman.domain.member.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {
	@Query("select m from Member m where m.email = :email")
	Optional<Member> findByEmail(@Param("email") String email);
}
