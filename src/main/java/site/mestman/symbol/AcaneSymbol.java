package site.mestman.symbol;

import java.util.stream.IntStream;

public class AcaneSymbol extends Symbol {
	public AcaneSymbol(int level, int growthForCurrentLevel, int requiredMaxLevelGrowth) {
		super(level, growthForCurrentLevel, requiredMaxLevelGrowth);
	}

	@Override
	public void validateFor(int level, int growthForCurrentLevel) {
		if (isLevelOutOfRange(level)) {
			throw new IllegalArgumentException("The level of the Arkane symbol must be between 1 and 20 levels.");
		}
		if (isGrowthOutOfRange(level, growthForCurrentLevel)) {
			throw new IllegalArgumentException(
				"The growth for the current level of the Arkane symbol must be between 1 and 2679.");
		}
		if (isGrowthNonZeroAtMaxLevelFor(level, growthForCurrentLevel)) {
			throw new IllegalArgumentException(
				"when the Arcane Symbol's max level is 20, growthForCurrentLevel must be 0.");
		}
	}

	@Override
	public boolean isLevelOutOfRange(int level) {
		return level < 1 || level > 20;
	}

	@Override
	public boolean isGrowthOutOfRange(int level, int growthForCurrentLevel) {
		if (level == 20 && growthForCurrentLevel == 0) {
			return false;
		}
		return growthForCurrentLevel < 1 || growthForCurrentLevel > 2679;
	}

	@Override
	public boolean isGrowthNonZeroAtMaxLevelFor(int level, int growthForCurrentLevel) {
		return level == 20 && growthForCurrentLevel != 0;
	}

	public int reduceGrowthBy(int level) {
		int[] requiredGrowthByLevel = {0, 12, 15, 20, 27, 36,
			47, 60, 75, 92, 111, 132,
			155, 180, 207, 236, 267,
			300, 335, 372, 0};

		return IntStream.rangeClosed(1, level - 1)
			.map(i -> requiredGrowthByLevel[i])
			.sum();
	}
}
