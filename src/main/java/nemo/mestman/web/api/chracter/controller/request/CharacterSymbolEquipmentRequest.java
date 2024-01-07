package nemo.mestman.web.api.chracter.controller.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import nemo.mestman.web.api.chracter.service.request.CharacterSymbolEquipmentServiceRequest;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class CharacterSymbolEquipmentRequest {
	@NotBlank(message = "캐릭터의 이름은 공백이면 안됩니다")
	private String characterName;

	public CharacterSymbolEquipmentServiceRequest toServiceRequest() {
		return new CharacterSymbolEquipmentServiceRequest(characterName);
	}
}
