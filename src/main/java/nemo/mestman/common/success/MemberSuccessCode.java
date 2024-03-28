package nemo.mestman.common.success;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MemberSuccessCode implements SuccessCode {
	OK_REGISTER_MEMBER(HttpStatus.OK, "회원 등록을 완료하였습니다"),
	OK_READ_MEMBER(HttpStatus.OK, "회원 목록 조회가 완료하였습니다"),
	OK_UPDATE_MEMBER(HttpStatus.OK, "회원 수정이 완료하였습니다"),
	OK_DELETE_MEMBER(HttpStatus.OK, "회원 삭제가 완료하였습니다");

	private final HttpStatus httpStatus;
	private final String message;
}