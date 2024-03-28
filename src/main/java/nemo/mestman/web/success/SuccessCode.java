package nemo.mestman.web.success;

import org.springframework.http.HttpStatus;

public interface SuccessCode {
	HttpStatus getHttpStatus();

	String getMessage();
}
