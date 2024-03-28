package nemo.mestman.domain.card.successcode;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import nemo.mestman.common.successcode.SuccessCode;

@Getter
@RequiredArgsConstructor
public enum CardSuccessCode implements SuccessCode {
	REGISTER_CARD(HttpStatus.OK, "카드 등록을 완료하였습니다"),
	READ_CARD(HttpStatus.OK, "카드 목록 조회가 완료하였습니다"),
	UPDATE_CARD(HttpStatus.OK, "카드 수정이 완료하였습니다"),
	DELETE_CARD(HttpStatus.OK, "카드 삭제가 완료하였습니다"),
	CONCAT_CARDS(HttpStatus.OK, "카드 연결이 완료하였습니다");

	private final HttpStatus httpStatus;
	private final String message;
}
