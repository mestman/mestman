package site.mestman.symbol;

import java.util.stream.IntStream;

public class AuthenticSymbol extends Symbol {

	public AuthenticSymbol(int level, int growthForCurrentLevel, int requiredMaxLevelGrowth) {
		super(level, growthForCurrentLevel, requiredMaxLevelGrowth);
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
