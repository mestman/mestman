package nemo.mestman.domain.symbol.calculator;

import java.time.LocalDate;

import nemo.mestman.domain.symbol.Symbol;

public class AcaneSymbolCalculator implements SymbolCalculator {
	private final int totalGrowthSymbol;

	public AcaneSymbolCalculator() {
		this.totalGrowthSymbol = 2679;
	}

	// 최소 일수 = floor((((필요 성장치 전체 개수 - (n 레벨 누적 성장치 + 현재 레벨 성장치)) / 1주 일일 및 주간 심볼 합계)) * 7일) + 2일(주간 심볼 포함)
	// 심볼 계산시 기준일자의 일일 심볼 포함하고 계산을 수행합니다.
	@Override
	public LocalDate calculate(Symbol symbol, LocalDate criteriaDate) {
		return criteriaDate.plusDays(symbol.calculateMinimumDays(totalGrowthSymbol));
	}

	@Override
	public int calculateRequiredTotalSymbolCount(Symbol symbol) {
		return symbol.calculateRequiredTotalSymbolCount(totalGrowthSymbol);
	}
}
