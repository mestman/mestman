package nemo.mestman.domain.roadmap.errorcode;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import nemo.mestman.common.error.code.ErrorCode;

@Getter
@RequiredArgsConstructor
public enum RoadMapErrorCode implements ErrorCode {
	NOT_FOUND(HttpStatus.NOT_FOUND, "로드맵이 존재하지 않습니다");

	private final HttpStatus httpStatus;
	private final String message;
}
