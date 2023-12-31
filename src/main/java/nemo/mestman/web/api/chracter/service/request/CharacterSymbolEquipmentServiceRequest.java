package nemo.mestman.web.api.chracter.service.request;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
public class CharacterSymbolEquipmentServiceRequest {
	private final String characterName;

	@Builder(access = AccessLevel.PUBLIC)
	public CharacterSymbolEquipmentServiceRequest(String characterName) {
		this.characterName = characterName;
	}
}
