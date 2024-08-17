package site.mestman.symbol.imple;

import java.util.stream.IntStream;

import site.mestman.symbol.Symbol;

public class AuthenticSymbol extends Symbol {

	private static final int MAX_LEVEL = 11;
	private static final int REQUIRED_MAX_LEVEL_GROWTH = 4565;
	private static final int[] REQUIRED_GROWTH_BY_LEVEL = {0, 29, 76, 141, 224, 325, 444, 581, 736, 909, 1100, 0};

	public AuthenticSymbol(int level, int growthForCurrentLevel) {
		super(level, growthForCurrentLevel, REQUIRED_MAX_LEVEL_GROWTH);
	}

	@Override
	public void validateFor(int level, int growthForCurrentLevel) {
		if (!isLevelOfRange(level)) {
			throw new IllegalArgumentException(
				"The level of the Authentic symbol must be between 1 and " + MAX_LEVEL + " levels.");
		}
		if (!isGrowthOfRange(level, growthForCurrentLevel)) {
			throw new IllegalArgumentException(
				"The growth for the current level of the Authentic symbol must be between 1 and "
					+ REQUIRED_MAX_LEVEL_GROWTH + ".");
		}
		if (isGrowthNonZeroAtMaxLevelFor(level, growthForCurrentLevel)) {
			throw new IllegalArgumentException(
				"when the Authentic Symbol's max level is " + MAX_LEVEL + ", growthForCurrentLevel must be 0.");
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

	@Override
	public int reduceGrowthBy(int level) {
		return IntStream.rangeClosed(1, level - 1)
			.map(i -> REQUIRED_GROWTH_BY_LEVEL[i])
			.sum();
	}
}
