package site.mestman.symbol;

import java.util.stream.IntStream;

public class AuthenticSymbol extends Symbol {

	public AuthenticSymbol(int level, int growthForCurrentLevel, int requiredMaxLevelGrowth) {
		super(level, growthForCurrentLevel, requiredMaxLevelGrowth);
	}

	@Override
	public void validateFor(int level, int growthForCurrentLevel) {
		if (isLevelOutOfRange(level)) {
			throw new IllegalArgumentException("The level of the Authentic symbol must be between 1 and 11 levels.");
		}
		if (isGrowthOutOfRange(level, growthForCurrentLevel)) {
			throw new IllegalArgumentException(
				"The growth for the current level of the Authentic symbol must be between 1 and 4565.");
		}
		if (isGrowthNonZeroAtMaxLevelFor(level, growthForCurrentLevel)) {
			throw new IllegalArgumentException(
				"when the Arcane Symbol's max level is 11, growthForCurrentLevel must be 0.");
		}
	}

	@Override
	public boolean isLevelOutOfRange(int level) {
		return level < 1 || level > 11;
	}

	@Override
	public boolean isGrowthOutOfRange(int level, int growthForCurrentLevel) {
		if (level == 11 && growthForCurrentLevel == 0) {
			return false;
		}
		return growthForCurrentLevel < 1 || growthForCurrentLevel > 4565;
	}

	@Override
	public boolean isGrowthNonZeroAtMaxLevelFor(int level, int growthForCurrentLevel) {
		return level == 11 && growthForCurrentLevel != 0;
	}

	@Override
	public int reduceGrowthBy(int level) {
		int[] requiredGrowthByLevel = {0, 29, 76, 141, 224, 325, 444, 581, 736, 909, 1100, 0};

		return IntStream.rangeClosed(1, level - 1)
			.map(i -> requiredGrowthByLevel[i])
			.sum();
	}
}
