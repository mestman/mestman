package nemo.mestman.web.api.member.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import nemo.mestman.domain.member.Member;
import nemo.mestman.domain.member.MemberRepository;
import nemo.mestman.web.api.member.request.MemberRegisterRequest;
import nemo.mestman.web.api.member.response.MemberRegisterResponse;

@Service
@RequiredArgsConstructor
public class MemberService {

	private final MemberRepository repository;

	@Transactional
	public MemberRegisterResponse registerMember(MemberRegisterRequest request) {
		Member member = repository.save(request.toEntity());
		return MemberRegisterResponse.from(member);
	}
}
