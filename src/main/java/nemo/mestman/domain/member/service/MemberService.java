package nemo.mestman.domain.member.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import nemo.mestman.domain.member.dto.request.MemberRegisterRequest;
import nemo.mestman.domain.member.dto.response.MemberRegisterResponse;
import nemo.mestman.domain.member.entity.Member;
import nemo.mestman.domain.member.policy.MemberSignUpPolicy;
import nemo.mestman.domain.member.repository.MemberRepository;

@Service
@RequiredArgsConstructor
public class MemberService {

	private final MemberRepository repository;
	private final MemberSignUpPolicy policy;

	@Transactional
	public MemberRegisterResponse registerMember(@Valid MemberRegisterRequest request) {
		policy.validate(request);
		Member member = repository.save(request.toEntity());
		return MemberRegisterResponse.from(member);
	}
}
