package nemo.mestman.domain.item.successcode;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import nemo.mestman.common.successcode.SuccessCode;

@Getter
@RequiredArgsConstructor
public enum MapleItemSuccessCode implements SuccessCode {
	REGISTER_ITEM(HttpStatus.OK, "아이템 등록을 완료하였습니다"),
	READ_ITEM(HttpStatus.OK, "아이템 목록 조회가 완료하였습니다"),
	UPDATE_ITEM(HttpStatus.OK, "아이템 수정이 완료하였습니다"),
	DELETE_ITEM(HttpStatus.OK, "아이템 삭제가 완료하였습니다");

	private final HttpStatus httpStatus;
	private final String message;
}
