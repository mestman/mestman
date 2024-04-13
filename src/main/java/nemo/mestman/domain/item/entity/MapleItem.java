package nemo.mestman.domain.item.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Transient;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import nemo.mestman.common.entity.BaseEntity;
import nemo.mestman.domain.card.entity.Card;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@ToString
@Entity
public class MapleItem extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	private Integer level;

	@Embedded
	private StarForce starForce;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "card_id", nullable = false)
	private Card card;

	@Transient
	@Builder.Default
	private List<ItemOption> options = new ArrayList<>();

	private MapleItem(String name, Integer level, StarForce starForce, Card card) {
		this.name = name;
		this.level = level;
		this.starForce = starForce;
		this.card = card;
	}

	public static MapleItem noCard(String name, int level) {
		StarForce startForce = StarForce.emptyBy(level);
		return new MapleItem(name, level, startForce, null);
	}

	public static MapleItem card(String name, int level, Card card) {
		StarForce startForce = StarForce.emptyBy(level);
		return new MapleItem(name, level, startForce, card);
	}

	public void addOption(ItemOption option) {
		options.add(option);
	}

	/**
	 * @return format: name:value:name:value ex) STR=10:DEX=10
	 * @see ItemOption#parse() STR
	 * @see ItemOption#parse() DEX
	 */
	public String parseItemOptions() {
		return options.stream()
			.map(ItemOption::parse)
			.collect(Collectors.joining(":"));
	}

	/**
	 * 아이템의 스타포스 증가량 수치 리스트를 반환
	 * @return 스타포스로 인한 증가된 수치 증가량 옵션 리스트
	 */
	public List<ItemOption> calStartForce() {
		return starForce.calOptions(level);
	}

	public void increaseStarForce(int value) {
		starForce.increase(value);
	}
}
