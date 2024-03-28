package nemo.mestman.domain.member.policy.validator;

import lombok.RequiredArgsConstructor;
import nemo.mestman.common.error.exception.MestmanException;
import nemo.mestman.common.validator.Validator;
import nemo.mestman.domain.member.dto.request.MemberRegisterRequest;
import nemo.mestman.domain.member.errorcode.MemberErrorCode;
import nemo.mestman.domain.member.repository.MemberRepository;

@RequiredArgsConstructor
public class EmailDuplicateValidator implements Validator<MemberRegisterRequest> {

	private final MemberRepository memberRepository;

	@Override
	public void validate(MemberRegisterRequest request) {
		if (memberRepository.findByEmail(request.getEmail()).isPresent()) {
			throw new MestmanException(MemberErrorCode.DUPLICATE_EMAIL);
		}
	}
}
