package site.mestman.symbol;

import java.time.LocalDate;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class Symbol {
	private final int level; // 레벨
	private final int growthForCurrentLevel; // 현재 성장치
	private final int requiredMaxLevelGrowth; // 최대 레벨 누적 성장치

	Symbol(int level, int growthForCurrentLevel, int requiredMaxLevelGrowth) {
		this.level = level;
		this.growthForCurrentLevel = growthForCurrentLevel;
		this.requiredMaxLevelGrowth = requiredMaxLevelGrowth;
		validateFor(level, growthForCurrentLevel);
	}

	public static Symbol arcane(int level, int growthForCurrentLevel) {
		return new AcaneSymbol(level, growthForCurrentLevel);
	}

	public static Symbol authentic(int level, int growthForCurrentLevel) {
		return new AuthenticSymbol(level, growthForCurrentLevel);
	}

	public abstract void validateFor(int level, int growthForCurrentLevel);

	public abstract boolean isLevelOfRange(int level);

	public abstract boolean isGrowthOfRange(int level, int growthForCurrentLevel);

	public abstract boolean isGrowthNonZeroAtMaxLevelFor(int level, int growthForCurrentLevel);

	// 현재 심볼이 최대 레벨을 달성하기 위해서 필요한 일자를 계산
	public LocalDate calculateCompletionDateForMaxLevel(int numberOfSymbolPerDay) {
		return LocalDate.now().plusDays(days(numberOfSymbolPerDay, requiredGrowth(reduceGrowth())));
	}

	// 누적 성장치 = 현재 레벨 누적 성장치 + 현재 레벨 성장치
	private int reduceGrowth() {
		return reduceGrowthBy(level) + growthForCurrentLevel;
	}

	// 현재 레벨에 따른 누적 성장치 계산
	public abstract int reduceGrowthBy(int level);

	// 필요한 성장치 = 요구되는 최대 성장치 - 누적 성장치
	private int requiredGrowth(int reduceGrowth) {
		return requiredMaxLevelGrowth - reduceGrowth;
	}

	// 만렙 달성 필요 일자 = (만렙 필요 성장치 - 누적 성장치) / 하루에 얻을 수 있는 심볼 개수
	private int days(int numberOfSymbolPerDay, int requiredGrowth) {
		int days = requiredGrowth / numberOfSymbolPerDay;
		if (requiredGrowth % numberOfSymbolPerDay != 0) {
			days++;
		}
		return days;
	}
}
