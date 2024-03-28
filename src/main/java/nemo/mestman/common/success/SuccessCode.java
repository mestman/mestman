package nemo.mestman.common.success;

import org.springframework.http.HttpStatus;

public interface SuccessCode {
	HttpStatus getHttpStatus();

	String getMessage();
}
