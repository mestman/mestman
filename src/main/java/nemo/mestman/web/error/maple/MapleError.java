package nemo.mestman.web.error.maple;

import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpServerErrorException;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class MapleError {

	private final String name;
	private final String message;

	@JsonCreator
	public MapleError(@JsonProperty("error") MapleErrorDetail error) {
		this.name = error.getName();
		this.message = error.getMessage();
	}

	public static MapleError internalServerError() {
		return new MapleError(new MapleErrorDetail("OPENAPI00001", "서버 내부 오류"));
	}

	public Throwable createException(String input) {
		String message = String.format("%s %s", this.message, input);
		if (isInternalServerError()) {
			return new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR, message);
		} else if (isForbidden()) {
			return new HttpServerErrorException(HttpStatus.FORBIDDEN, message);
		} else if (isInvalidIdentifier()) {
			return new HttpServerErrorException(HttpStatus.BAD_REQUEST, message);
		} else if (isMissingParameterOrInvalidInput()) {
			return new HttpServerErrorException(HttpStatus.BAD_REQUEST, message);
		} else if (isInvalidAPIKey()) {
			return new HttpServerErrorException(HttpStatus.BAD_REQUEST, message);
		} else if (isInvalidGameOrAPIPath()) {
			return new HttpServerErrorException(HttpStatus.BAD_REQUEST, message);
		} else if (isApiCallsExceeded()) {
			return new HttpServerErrorException(HttpStatus.TOO_MANY_REQUESTS, message);
		}
		return new HttpServerErrorException(HttpStatus.BAD_REQUEST, message);
	}

	private boolean isInternalServerError() {
		return name.equals("OPENAPI00001");
	}

	private boolean isForbidden() {
		return name.equals("OPENAPI00002");
	}

	private boolean isInvalidIdentifier() {
		return name.equals("OPENAPI00003");
	}

	private boolean isMissingParameterOrInvalidInput() {
		return name.equals("OPENAPI00004");
	}

	private boolean isInvalidAPIKey() {
		return name.equals("OPENAPI00005");
	}

	private boolean isInvalidGameOrAPIPath() {
		return name.equals("OPENAPI00006");
	}

	private boolean isApiCallsExceeded() {
		return name.equals("OPENAPI00007");
	}
}
