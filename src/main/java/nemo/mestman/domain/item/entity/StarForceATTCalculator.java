package nemo.mestman.domain.item.entity;

import java.util.stream.IntStream;

public class StarForceATTCalculator implements StarForceCalculator {
	private final int[] level130 = {7, 8, 9, 10, 11};
	private final int[] level140 = {8, 9, 10, 11, 12, 13, 15, 17, 19, 21};
	private final int[] level150 = {9, 10, 11, 12, 13, 14, 16, 18, 20, 22};
	private final int[] level160 = {10, 11, 12, 13, 14, 15, 17, 19, 21, 23};
	private final int[] level200 = {12, 13, 14, 15, 16, 17, 19, 21, 23, 25};

	@Override
	public int calculate(int starForce, int level) {
		int startStarForce = 16;
		int[] table = getLevelTable(level);
		return IntStream.rangeClosed(startStarForce, starForce)
			.map(i -> table[i - startStarForce])
			.sum();
	}

	private int[] getLevelTable(int level) {
		if (level >= 130 && level < 140) {
			return level130;
		} else if (level >= 140 && level < 150) {
			return level140;
		} else if (level >= 150 && level < 160) {
			return level150;
		} else if (level >= 160 && level < 200) {
			return level160;
		} else {
			return level200;
		}
	}
}
