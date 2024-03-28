package nemo.mestman.domain.roadmap.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import nemo.mestman.domain.roadmap.entity.RoadMap;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
public class RoadMapRegisterRequest {
	@NotNull(message = "필수 정보입니다")
	@NotBlank(message = "로드맵 이름은 필수 정보입니다")
	private String name;
	private String characterClass;

	public static RoadMapRegisterRequest create(String name, String characterClass) {
		return new RoadMapRegisterRequest(name, characterClass);
	}

	public RoadMap toEntity() {
		return RoadMap.builder()
			.name(name)
			.characterClass(characterClass)
			.build();
	}
}
