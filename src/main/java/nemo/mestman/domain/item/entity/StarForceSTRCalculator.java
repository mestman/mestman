package nemo.mestman.domain.item.entity;

import java.util.stream.IntStream;

public class StarForceSTRCalculator implements StarForceCalculator {

	private final int[] level94 = {2, 2, 2, 2, 2};
	private final int[] level107 = {2, 2, 2, 2, 2, 3, 3, 3};

	private final int[] level117 = {2, 2, 2, 2, 2, 3, 3, 3, 3, 3};
	private final int[] level127 = {2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3};
	private final int[] level130 = {2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 7, 7, 7, 7, 7};
	private final int[] level140 = {2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 9, 9, 9, 9, 9, 9, 9, 0, 0, 0};

	private final int[] level150 = {2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 11, 11, 11, 11, 11, 11, 11, 0, 0, 0};

	private final int[] level160 = {2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 13, 13, 13, 13, 13, 13, 13, 0, 0, 0};

	private final int[] level200 = {2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 15, 15, 15, 15, 15, 15, 15, 0, 0, 0};

	@Override
	public int calculate(int starForce, int level) {
		int[] statTable = getStatTable(level);
		return IntStream.rangeClosed(1, starForce)
			.map(i -> statTable[i - 1])
			.sum();
	}

	private int[] getStatTable(int level) {
		if (level >= 94 && level < 107) {
			return level94;
		} else if (level >= 107 && level < 117) {
			return level107;
		} else if (level >= 117 && level < 127) {
			return level117;
		} else if (level >= 127 && level < 130) {
			return level127;
		} else if (level >= 130 && level < 140) {
			return level130;
		} else if (level >= 140 && level < 150) {
			return level140;
		} else if (level >= 150 && level < 160) {
			return level150;
		} else if (level >= 160 && level < 170) {
			return level160;
		}
		return level200;
	}
}
