package nemo.mestman.domain.card.errorcode;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import nemo.mestman.common.error.code.ErrorCode;

@Getter
@RequiredArgsConstructor
public enum CardErrorCode implements ErrorCode {
	NOT_FOUND(HttpStatus.NOT_FOUND, "카드를 찾을 수 없습니다");

	private final HttpStatus httpStatus;
	private final String message;
}
