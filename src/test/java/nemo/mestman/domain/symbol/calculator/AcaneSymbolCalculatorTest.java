package nemo.mestman.domain.symbol.calculator;

import java.time.LocalDate;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import nemo.mestman.domain.symbol.Symbol;
import nemo.mestman.domain.symbol.SymbolDetail;

class AcaneSymbolCalculatorTest {

	@DisplayName("현재 심볼에 대한 필요한 최소 일수를 계산한다")
	@CsvSource(value = {"1,0,101", "2,8,100", "20,0,0"})
	@ParameterizedTest
	void calculate(int level, int growthCount, int expectedDays) {
		// given
		SymbolCalculator calculator = new AcaneSymbolCalculator();
		Symbol symbol = createAcaneSymbol(SymbolDetail.Name.ACANE1, level, growthCount);
		LocalDate criteriaDate = LocalDate.of(2024, 1, 1);

		// when
		LocalDate actual = calculator.calculate(symbol, criteriaDate);

		// then
		LocalDate expected = criteriaDate.plusDays(expectedDays);
		Assertions.assertThat(actual).isEqualTo(expected);
	}

	private Symbol createAcaneSymbol(SymbolDetail.Name name, int level, int growthCount) {
		return Symbol.acane(createSymbolDetail(name, level, growthCount));
	}

	private SymbolDetail createSymbolDetail(SymbolDetail.Name name, int level, int growthCount) {
		return SymbolDetail.builder()
			.symbolName(name)
			.symbolLevel(level)
			.symbolGrowthCount(growthCount)
			.build();
	}
}
