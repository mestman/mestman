package nemo.mestman.common.error.handler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpServerErrorException;

import nemo.mestman.common.api.ApiResponse;
import nemo.mestman.common.error.exception.MestmanException;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(value = {HttpServerErrorException.class})
	public ResponseEntity<ApiResponse<Object>> handleHttpServerErrorException(HttpServerErrorException e) {
		return ResponseEntity.badRequest()
			.body(ApiResponse.badRequest(e.getMessage(), null));
	}

	@ExceptionHandler(value = {MestmanException.class})
	public ResponseEntity<ApiResponse<Object>> handleMestmanException(MestmanException e) {
		return ResponseEntity.status(e.getErrorCode().getHttpStatus())
			.body(ApiResponse.from(e.getErrorCode()));
	}
}
