package nemo.mestman.domain.member.errorcode;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import nemo.mestman.common.error.code.ErrorCode;

@Getter
@RequiredArgsConstructor
public enum MemberErrorCode implements ErrorCode {
	NOT_MATCH_PASSWORD(HttpStatus.BAD_REQUEST, "비밀번호가 일치하지 않습니다"),
	DUPLICATE_EMAIL(HttpStatus.CONFLICT, "이메일이 중복되었습니다");

	private final HttpStatus httpStatus;
	private final String message;
}
