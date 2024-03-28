package nemo.mestman.common.error.maple;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;

@Getter
public class MapleErrorDetail {
	private final String name;
	private final String message;

	@JsonCreator
	public MapleErrorDetail(@JsonProperty("name") String name, @JsonProperty("message") String message) {
		this.name = name;
		this.message = message;
	}
}
