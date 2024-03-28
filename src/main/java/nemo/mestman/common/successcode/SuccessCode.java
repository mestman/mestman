package nemo.mestman.common.successcode;

import org.springframework.http.HttpStatus;

public interface SuccessCode {
	HttpStatus getHttpStatus();

	String getMessage();
}
