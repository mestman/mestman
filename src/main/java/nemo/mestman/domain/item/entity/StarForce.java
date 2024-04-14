package nemo.mestman.domain.item.entity;

import static nemo.mestman.domain.item.entity.ItemOption.OptionType.*;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Transient;
import lombok.Getter;
import nemo.mestman.domain.item.entity.ItemOption.OptionType;

@Getter
@Embeddable
public class StarForce {
	private static final int LIMIT = 25;
	@Column(name = "start_force")
	private int value;
	@Transient
	private final int maxStars;

	@Transient
	private final List<StarForceCalculator> calculators = List.of(
		new StarForceStatCalculator(),
		new StarForceHpCalculator(),
		new StarForceATTCalculator()
	);

	private StarForce(int value, int maxStars) {
		if (value < 0 || value > LIMIT) {
			throw new IllegalArgumentException("Invalid star force value: " + value);
		}
		if (maxStars < 0 || maxStars > LIMIT) {
			throw new IllegalArgumentException("Invalid max star force value: " + maxStars);
		}
		this.value = value;
		this.maxStars = maxStars;
	}

	protected StarForce() {
		this.value = 0;
		this.maxStars = 0;
	}

	public static StarForce create(int value, int maxStars) {
		return new StarForce(value, maxStars);
	}

	public static StarForce emptyBy(int level) {
		int maxStars = getMaxStarsBy(level);
		return new StarForce(0, maxStars);
	}

	private static int getMaxStarsBy(int level) {
		if (level >= 0 && level <= 94) {
			return 5;
		} else if (level >= 95 && level <= 107) {
			return 8;
		} else if (level >= 108 && level <= 117) {
			return 10;
		} else if (level >= 118 && level <= 127) {
			return 15;
		} else if (level >= 128 && level <= 137) {
			return 20;
		}
		return 25;
	}

	/**
	 * 스타포스 value값에 따라서 증가된 옵션들을 반환
	 * @return 아이템 수치 옵션
	 */
	public List<ItemOption> calOptions(int level) {
		List<OptionType> types = List.of(STR, DEX, HP, ATT);

		List<ItemOption> result = new ArrayList<>();
		for (OptionType type : types) {
			int stat = 0;
			if (type.equals(STR) || type.equals(DEX)) {
				stat = calculators.get(0).calculate(value, level);
			} else if (type.equals(HP)) {
				stat = calculators.get(1).calculate(value, level);
			} else if (type.equals(ATT)) {
				stat = calculators.get(2).calculate(value, level);
			}
			result.add(ItemOption.byType(type, stat));
		}
		return result;
	}

	public void increase(int value) {
		this.value += value;
	}
}
