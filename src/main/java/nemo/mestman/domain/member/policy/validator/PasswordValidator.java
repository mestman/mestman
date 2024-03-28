package nemo.mestman.domain.member.policy.validator;

import nemo.mestman.common.error.exception.MestmanException;
import nemo.mestman.common.validator.Validator;
import nemo.mestman.domain.member.dto.request.MemberRegisterRequest;
import nemo.mestman.domain.member.errorcode.MemberErrorCode;

public class PasswordValidator implements Validator<MemberRegisterRequest> {
	@Override
	public void validate(MemberRegisterRequest request) {
		if (!request.getPassword().equals(request.getPasswordConfirm())) {
			throw new MestmanException(MemberErrorCode.NOT_MATCH_PASSWORD);
		}
	}
}
