package nemo.mestman.web.api.chracter.controller.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import nemo.mestman.web.api.chracter.service.request.CharacterSymbolEquipmentServiceRequest;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CharacterSymbolEquipmentRequest {
	private String characterName;

	public CharacterSymbolEquipmentServiceRequest toServiceRequest() {
		return new CharacterSymbolEquipmentServiceRequest(characterName);
	}
}
