package nemo.mestman.web.api.chracter.service.request;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class SymbolMinDaysServiceRequest {
	private final String characterName;
}
