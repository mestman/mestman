package nemo.mestman.domain.symbol;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AcaneSymbol extends Symbol {

	private static final int MAX_LEVEL = 20;
	private static final int WEEKLY_SYMBOL = 45;
	private static final int DAILY_SYMBOL = 20;

	private final int[] cumulativeGrowthValue;

	public AcaneSymbol(SymbolDetail symbolDetail, Integer totalWeeklySymbol) {
		super(symbolDetail, totalWeeklySymbol);
		this.cumulativeGrowthValue = initCumulativeGrowthValue(MAX_LEVEL);
	}

	private int[] initCumulativeGrowthValue(int level) {
		int[] result = new int[level + 1];
		for (int i = 2; i < result.length; i++) {
			result[i] = result[i - 1] + calculateRequiredGrowthDeviceForNLevel(i - 1);
		}
		return result;
	}

	// n레벨 필요 성장치
	private int calculateRequiredGrowthDeviceForNLevel(int n) {
		return (n * n) + 11;
	}

	@Override
	public int calculateMinimumDays(int totalGrowthSymbol) {
		// 필요한 심볼 개수
		int requiredSymbol = totalGrowthSymbol - calculateCurrentGrowth();

		// 최소주간, 14주
		int totalWeeklySymbol = getTotalWeeklySymbol();
		int minimumWeek = requiredSymbol / totalWeeklySymbol;

		// (14주 * 185) = 얻을 수 있는 심볼개수
		int totalSymbolCount = minimumWeek * totalWeeklySymbol;

		// 남은 심볼 개수 = 필요한 심볼 개수 - 얻을 수 있는 심볼 개수
		int remainRequiredSymbolCount = requiredSymbol - totalSymbolCount;

		int remainDays = (int)Math.ceil((double)(remainRequiredSymbolCount - WEEKLY_SYMBOL) / DAILY_SYMBOL);

		// 남은 일수 계산
		if (remainRequiredSymbolCount == 0) {
			return 0;
		}
		if (remainRequiredSymbolCount < WEEKLY_SYMBOL || remainDays == 0) {
			return toDays(minimumWeek) + 1;
		}
		return toDays(minimumWeek) + remainDays;
	}

	private int toDays(int minimumWeek) {
		return minimumWeek * 7;
	}

	// 현재 성장치 = n레벨 누적 성장치 + n레벨 성장치
	@Override
	public int calculateCurrentGrowth() {
		int level = getSymbolDetail().getSymbolLevel();
		return cumulativeGrowthValue[level] + getSymbolDetail().getSymbolGrowthCount();
	}

	@Override
	public int calculateRequiredTotalSymbolCount(int totalGrowthSymbol) {
		return totalGrowthSymbol - calculateCurrentGrowth();
	}
}
