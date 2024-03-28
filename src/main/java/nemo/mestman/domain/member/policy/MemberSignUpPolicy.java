package nemo.mestman.domain.member.policy;

import java.util.List;

import lombok.RequiredArgsConstructor;
import nemo.mestman.common.validator.Validator;
import nemo.mestman.domain.member.dto.request.MemberRegisterRequest;

@RequiredArgsConstructor
public class MemberSignUpPolicy {
	private final List<Validator<MemberRegisterRequest>> validators;

	public void validate(MemberRegisterRequest request) {
		validators.forEach(v -> v.validate(request));
	}
}
