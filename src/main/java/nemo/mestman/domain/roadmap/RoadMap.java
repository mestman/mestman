package nemo.mestman.domain.roadmap;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import nemo.mestman.domain.BaseEntity;
import nemo.mestman.domain.member.Member;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Entity
public class RoadMap extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;
	private String characterClass;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id")
	private Member member;

	public static RoadMap create(String name, String characterClass, Member member) {
		return RoadMap.builder()
			.name(name)
			.characterClass(characterClass)
			.member(member)
			.build();
	}

	public void change(String name, String characterClass) {
		this.name = name;
		this.characterClass = characterClass;
	}
}
