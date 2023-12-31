package nemo.mestman.domain.symbol.calculator;

import java.time.LocalDate;

import nemo.mestman.domain.symbol.Symbol;

public interface SymbolCalculator {

	LocalDate calculate(Symbol symbol, LocalDate criteriaDate);

	int calculateRequiredTotalSymbolCount(Symbol symbol);
}
