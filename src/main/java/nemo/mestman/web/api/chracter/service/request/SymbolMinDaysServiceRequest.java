package nemo.mestman.web.api.chracter.service.request;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;

@Getter
public class SymbolMinDaysServiceRequest {
	private final String characterName;

	@Builder(access = AccessLevel.PUBLIC)
	public SymbolMinDaysServiceRequest(String characterName) {
		this.characterName = characterName;
	}
}
