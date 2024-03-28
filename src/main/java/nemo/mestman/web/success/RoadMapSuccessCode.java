package nemo.mestman.web.success;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum RoadMapSuccessCode implements SuccessCode {
	OK_REGISTER_ROADMAP(HttpStatus.OK, "로드맵 등록을 완료하였습니다"),
	OK_READ_ROADMAPS(HttpStatus.OK, "로드맵 목록 조회가 완료하였습니다"),
	OK_UPDATE_ROADMAP(HttpStatus.OK, "로드맵 수정이 완료하였습니다"),
	OK_DELETE_ROADMAP(HttpStatus.OK, "로드맵 삭제가 완료하였습니다");

	private final HttpStatus httpStatus;
	private final String message;
}
