package nemo.mestman.common.api;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.ToString;
import nemo.mestman.common.error.code.ErrorCode;
import nemo.mestman.common.successcode.SuccessCode;

@ToString
@Getter
public class ApiResponse<T> {

	private final int code;
	private final String status;
	private final String message;
	private final T data;

	public ApiResponse(HttpStatus httpStatus, String message, T data) {
		this.code = httpStatus.value();
		this.status = httpStatus.getReasonPhrase();
		this.message = message;
		this.data = data;
	}

	public static <T> ApiResponse<T> ok(String message, T data) {
		return new ApiResponse<>(HttpStatus.OK, message, data);
	}

	public static <T> ApiResponse<T> badRequest(String message, T data) {
		return new ApiResponse<>(HttpStatus.BAD_REQUEST, message, data);
	}

	public static <T> ApiResponse<T> from(SuccessCode code) {
		return new ApiResponse<>(code.getHttpStatus(), code.getMessage(), null);
	}

	public static <T> ApiResponse<T> from(SuccessCode code, T data) {
		return new ApiResponse<>(code.getHttpStatus(), code.getMessage(), data);
	}

	public static ApiResponse<Object> from(ErrorCode e) {
		return new ApiResponse<>(e.getHttpStatus(), e.getMessage(), null);
	}
}
