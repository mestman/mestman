package site.mestman.symbol;

import java.time.LocalDate;

import lombok.extern.slf4j.Slf4j;
import site.mestman.symbol.imple.AcaneSymbol;
import site.mestman.symbol.imple.AuthenticSymbol;

@Slf4j
public abstract class Symbol {
	private final int level; // 레벨
	private final int growthForCurrentLevel; // 현재 성장치
	private final int requiredMaxLevelGrowth; // 최대 레벨 누적 성장치
	private final int dailySymbols; // 하루에 얻을 수 있는 심볼 개수

	protected Symbol(int level, int growthForCurrentLevel, int requiredMaxLevelGrowth, int dailySymbols) {
		this.level = level;
		this.growthForCurrentLevel = growthForCurrentLevel;
		this.requiredMaxLevelGrowth = requiredMaxLevelGrowth;
		this.dailySymbols = dailySymbols;
		// TODO: numberOfSymbolPerDay 검증문 추가
		validateFor(level, growthForCurrentLevel);
	}

	public static Symbol arcane(int level) {
		return new AcaneSymbol(level, 1);
	}

	public static Symbol arcane(int level, int growthForCurrentLevel) {
		return new AcaneSymbol(level, growthForCurrentLevel);
	}

	public static Symbol cernium(int level, int growthForCurrentLevel) {
		return new AuthenticSymbol(level, growthForCurrentLevel, 20);
	}

	public static Symbol basic(int level, int growthForCurrentLevel) {
		return new AuthenticSymbol(level, growthForCurrentLevel, 10);
	}

	public abstract void validateFor(int level, int growthForCurrentLevel);

	public abstract boolean isLevelOfRange(int level);

	public abstract boolean isGrowthOfRange(int level, int growthForCurrentLevel);

	public abstract boolean isGrowthNonZeroAtMaxLevelFor(int level, int growthForCurrentLevel);

	// 현재 심볼이 최대 레벨을 달성하기 위해서 필요한 일자를 계산
	public LocalDate calculateCompletionDateForMaxLevel(int dailySymbols) {
		return LocalDate.now().plusDays(days(this.dailySymbols, requiredGrowth(reduceGrowth())));
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
