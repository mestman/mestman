package nemo.mestman.web.api.member.service;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import nemo.mestman.AbstractContainerBaseTest;
import nemo.mestman.domain.member.dto.request.MemberRegisterRequest;
import nemo.mestman.domain.member.dto.response.MemberRegisterResponse;
import nemo.mestman.domain.member.entity.Member;
import nemo.mestman.domain.member.service.MemberService;

class MemberServiceTest extends AbstractContainerBaseTest {

	@Autowired
	private MemberService service;

	@DisplayName("사용자는 회원으로 등록한다")
	@Test
	void registerMember() {
		// given
		String email = "user1@gmail.com";
		String nickname = "user1";
		String password = "password";
		String passwordConfirm = "password";
		MemberRegisterRequest request = MemberRegisterRequest.create(email, nickname, password, passwordConfirm);
		// when
		MemberRegisterResponse response = service.registerMember(request);
		// then
		Member findMember = memberRepository.findById(response.getId()).orElseThrow();
		assertAll(
			() -> assertThat(response.getId()).isNotNull(),
			() -> assertThat(response.getEmail()).isEqualTo(email),
			() -> assertThat(response.getNickname()).isEqualTo(nickname),
			() -> assertThat(findMember).isNotNull()
		);
	}

}
