package nemo.mestman.domain.chracter.controller.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;
import nemo.mestman.domain.chracter.service.request.SymbolMinDaysServiceRequest;

@ToString
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class CharacterSymbolEquipmentRequest {
	@NotBlank(message = "캐릭터의 이름은 공백이면 안됩니다")
	private String characterName;

	public SymbolMinDaysServiceRequest toServiceRequest() {
		return SymbolMinDaysServiceRequest.builder()
			.characterName(characterName)
			.build();
	}
}
