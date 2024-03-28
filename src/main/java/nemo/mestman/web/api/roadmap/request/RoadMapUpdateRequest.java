package nemo.mestman.web.api.roadmap.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import nemo.mestman.domain.roadmap.RoadMap;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
public class RoadMapUpdateRequest {
	@NotBlank(message = "로드맵 이름은 필수 정보입니다")
	private String name;
	private String characterClass;

	public static RoadMapUpdateRequest create(String name, String characterClass) {
		return new RoadMapUpdateRequest(name, characterClass);
	}

	public RoadMap toEntity() {
		return RoadMap.builder()
			.name(name)
			.characterClass(characterClass)
			.build();
	}
}
