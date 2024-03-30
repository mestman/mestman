package nemo.mestman.domain.card.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import nemo.mestman.common.entity.BaseEntity;
import nemo.mestman.domain.item.entity.MapleItem;
import nemo.mestman.domain.roadmap.entity.RoadMap;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
@Entity
@ToString
@EqualsAndHashCode(of = {"id"}, callSuper = false)
public class Card extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String title;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "road_map_id")
	private RoadMap roadMap;

	@OneToMany(fetch = FetchType.LAZY)
	private List<MapleItem> items = new ArrayList<>();

	public static Card create(String title, RoadMap roadMap) {
		return Card.builder()
			.title(title)
			.roadMap(roadMap)
			.build();
	}

	public void change(Card card) {
		this.title = card.title;
		this.roadMap = card.roadMap;
	}
}
