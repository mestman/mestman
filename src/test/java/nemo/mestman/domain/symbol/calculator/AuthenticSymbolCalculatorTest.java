package nemo.mestman.domain.symbol.calculator;

import java.time.LocalDate;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import nemo.mestman.domain.symbol.AuthenticSymbol;
import nemo.mestman.domain.symbol.Symbol;
import nemo.mestman.domain.symbol.SymbolDetail;

class AuthenticSymbolCalculatorTest {

	@DisplayName("현재 세르니움 심볼에 대한 필요한 최소 일수를 계산한다")
	@CsvSource(value = {"1,0,229"})
	@ParameterizedTest
	void calculateWithCernium(int level, int growthCount, int expectedDays) {
		// given
		Symbol symbol = createAuthenticSymbol(SymbolDetail.Name.AUTHENTIC1, level, growthCount);
		SymbolCalculator calculator = new AuthenticSymbolCalculator();
		LocalDate criteriaDate = LocalDate.of(2024, 1, 1);

		// when
		LocalDate actual = calculator.calculate(symbol, criteriaDate);

		// then
		LocalDate expected = criteriaDate.plusDays(expectedDays);
		Assertions.assertThat(actual).isEqualTo(expected);
	}

	@DisplayName("현재 세르니움을 제외한 심볼에 대한 필요한 최소 일수를 계산한다")
	@CsvSource(value = {"1,0,457"})
	@ParameterizedTest
	void calculate(int level, int growthCount, int expectedDays) {
		// given
		SymbolCalculator calculator = new AuthenticSymbolCalculator();
		Symbol symbol = createAuthenticSymbol(SymbolDetail.Name.AUTHENTIC2, level, growthCount);
		LocalDate criteriaDate = LocalDate.of(2024, 1, 1);

		// when
		LocalDate actual = calculator.calculate(symbol, criteriaDate);

		// then
		LocalDate expected = criteriaDate.plusDays(expectedDays);
		Assertions.assertThat(actual).isEqualTo(expected);
	}

	private Symbol createAuthenticSymbol(SymbolDetail.Name name, int level, int growthCount) {
		return AuthenticSymbol.authentic(createSymbolDetail(name, level, growthCount));
	}

	private SymbolDetail createSymbolDetail(SymbolDetail.Name name, int level, int growthCount) {
		return SymbolDetail.builder()
			.symbolName(name)
			.symbolLevel(level)
			.symbolGrowthCount(growthCount)
			.build();
	}
}
