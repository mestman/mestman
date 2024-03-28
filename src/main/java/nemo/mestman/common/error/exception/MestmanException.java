package nemo.mestman.common.error.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import nemo.mestman.common.error.code.ErrorCode;

@Getter
@RequiredArgsConstructor
@ToString
public class MestmanException extends RuntimeException {
	private final ErrorCode errorCode;
}
