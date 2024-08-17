package site.mestman.symbol;

import java.util.stream.IntStream;

public class AcaneSymbol extends Symbol {
	private static final int MAX_LEVEL = 20;
	private static final int REQUIRED_MAX_LEVEL_GROWTH = 2679;
	private static final int[] REQUIRED_GROWTH_BY_LEVEL = {
		0, 12, 15, 20, 27, 36,
		47, 60, 75, 92, 111, 132,
		155, 180, 207, 236, 267,
		300, 335, 372, 0
	};

	public AcaneSymbol(int level, int growthForCurrentLevel, int requiredMaxLevelGrowth) {
		super(level, growthForCurrentLevel, requiredMaxLevelGrowth);
	}

	@Override
	public void validateFor(int level, int growthForCurrentLevel) {
		if (!isLevelOfRange(level)) {
			throw new IllegalArgumentException(
				"The level of the Acane symbol must be between 1 and " + MAX_LEVEL + " levels.");
		}
		if (!isGrowthOfRange(level, growthForCurrentLevel)) {
			throw new IllegalArgumentException(
				"The growth for the current level of the Acane symbol must be between 1 and "
					+ REQUIRED_MAX_LEVEL_GROWTH + ".");
		}
		if (isGrowthNonZeroAtMaxLevelFor(level, growthForCurrentLevel)) {
			throw new IllegalArgumentException(
				"when the Arcane Symbol's max level is " + MAX_LEVEL + ", growthForCurrentLevel must be 0.");
		}
	}

	@Override
	public boolean isLevelOfRange(int level) {
		return level >= 1 && level <= MAX_LEVEL;
	}

	@Override
	public boolean isGrowthOfRange(int level, int growthForCurrentLevel) {
		if (level == MAX_LEVEL && growthForCurrentLevel == 0) {
			return true;
		}
		return growthForCurrentLevel >= 1 && growthForCurrentLevel <= REQUIRED_MAX_LEVEL_GROWTH;
	}

	@Override
	public boolean isGrowthNonZeroAtMaxLevelFor(int level, int growthForCurrentLevel) {
		return level == MAX_LEVEL && growthForCurrentLevel != 0;
	}

	public int reduceGrowthBy(int level) {
		return IntStream.rangeClosed(1, level - 1)
			.map(i -> REQUIRED_GROWTH_BY_LEVEL[i])
			.sum();
	}
}
