package nemo.mestman.web.error.handler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpServerErrorException;

import nemo.mestman.web.common.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(value = {HttpServerErrorException.class})
	public ResponseEntity<ApiResponse<Object>> handleHttpServerErrorException(HttpServerErrorException e) {
		return ResponseEntity.badRequest()
			.body(ApiResponse.badRequest(e.getMessage(), null));
	}
}
