package nemo.mestman.domain.symbol;

import java.time.LocalDate;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import nemo.mestman.domain.symbol.calculator.AcaneSymbolCalculator;
import nemo.mestman.domain.symbol.calculator.AuthenticSymbolCalculator;
import nemo.mestman.domain.symbol.calculator.SymbolCalculator;
import nemo.mestman.web.api.chracter.controller.response.SymbolMinimumDays;

@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class Symbol {
	private SymbolDetail symbolDetail;
	private Integer totalWeeklySymbol;

	public static Symbol acane(SymbolDetail symbolDetail) {
		return new AcaneSymbol(symbolDetail, 185);
	}

	public static Symbol authentic(SymbolDetail symbolDetail) {
		if (symbolDetail.isCernium()) {
			return new AuthenticSymbol(symbolDetail, 140);
		}
		return new AuthenticSymbol(symbolDetail, 70);
	}

	public abstract int calculateCurrentGrowth();

	public abstract int calculateMinimumDays(int totalGrowthSymbol);

	public abstract int calculateRequiredTotalSymbolCount(int totalGrowthSymbol);

	public SymbolMinimumDays createSymbolMinimumDays(LocalDate criteriaDate) {
		SymbolCalculator acaneSymbolCalculator = new AcaneSymbolCalculator();
		SymbolCalculator authenticSymbolCalculator = new AuthenticSymbolCalculator();
		if (symbolDetail.isAcane()) {
			LocalDate minimumDate = acaneSymbolCalculator.calculate(this, criteriaDate);
			int requiredTotalSymbolCount = acaneSymbolCalculator.calculateRequiredTotalSymbolCount(this);
			return SymbolMinimumDays.of(symbolDetail, requiredTotalSymbolCount, minimumDate);
		}
		LocalDate minimumDate = authenticSymbolCalculator.calculate(this, criteriaDate);
		int requiredTotalSymbolCount = authenticSymbolCalculator.calculateRequiredTotalSymbolCount(this);
		return SymbolMinimumDays.of(symbolDetail, requiredTotalSymbolCount, minimumDate);
	}
}
