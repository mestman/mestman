package nemo.mestman.domain.symbol.calculator;

import java.time.LocalDate;

import nemo.mestman.domain.symbol.Symbol;

public class AuthenticSymbolCalculator implements SymbolCalculator {

	private final int totalGrowthSymbol;

	public AuthenticSymbolCalculator() {
		this.totalGrowthSymbol = 4565;
	}

	@Override
	public LocalDate calculate(Symbol symbol, LocalDate criteriaDate) {
		return criteriaDate.plusDays(symbol.calculateMinimumDays(totalGrowthSymbol));
	}

	@Override
	public int calculateRequiredTotalSymbolCount(Symbol symbol) {
		return symbol.calculateRequiredTotalSymbolCount(totalGrowthSymbol);
	}
}
