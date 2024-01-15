package nemo.mestman.domain.symbol;

import static org.assertj.core.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import nemo.mestman.web.api.chracter.controller.response.SymbolMinDays;

class SymbolTest {

	@DisplayName("n레벨에 해당하는 누적 성장치 + 현재 성장치의 합계를 계산합니다.")
	@CsvSource(value = {"1,0,0", "2,0,12", "3,0,27", "18,10,1982", "19,10,2317", "20,0,2679"})
	@ParameterizedTest
	void calculateCurrentGrowth(int level, int growthCount, int expected) {
		// given
		SymbolDetail symbolDetail = createSymbolDetail(SymbolDetail.Name.ACANE1, level, growthCount);
		Symbol symbol = Symbol.acane(symbolDetail);

		// when
		int actual = symbol.calculateCurrentGrowth();

		// then
		assertThat(actual).isEqualTo(expected);
	}

	@DisplayName("심볼의 최소 일수 계산한다")
	@CsvSource(value = {"1,101", "2,100", "3,99", "4,99", "5,99", "19,15", "20,0"})
	@ParameterizedTest
	void calculateMinimumDays(int level, int expected) {
		// given
		SymbolDetail symbolDetail = createSymbolDetail(SymbolDetail.Name.ACANE1, level, 0);
		Symbol symbol = Symbol.acane(symbolDetail);

		// when
		int actual = symbol.calculateMinimumDays(2679);

		// then
		assertThat(actual).isEqualTo(expected);
	}

	@DisplayName("심볼에 대한 최소일수 객체를 생성한다")
	@CsvSource(value = {"1,101", "2,100", "3,99", "4,99", "5,99", "19,15", "20,0"})
	@ParameterizedTest
	void createSymbolMinimumDays(int level, int expected) {
		// given
		SymbolDetail symbolDetail = createSymbolDetail(SymbolDetail.Name.ACANE1, level, 0);
		Symbol symbol = Symbol.acane(symbolDetail);

		// when
		SymbolMinDays actual = symbol.createSymbolMinimumDays(createCriteriaDate());

		// then
		assertThat(actual)
			.extracting("name", "minimumDate")
			.containsExactlyInAnyOrder(SymbolDetail.Name.ACANE1, createCriteriaDate().plusDays(expected));
	}

	@DisplayName("어센틱 심볼 : 세르니움의 최소 일수 계산한다")
	@CsvSource(value = {"1,229", "2,227", "3,223", "4,216", "5,205", "10,55", "11,0"})
	@ParameterizedTest
	void calculateMinimumDaysForCerniumAuthentic(int level, int expected) {
		// given
		SymbolDetail symbolDetail = createSymbolDetail(SymbolDetail.Name.AUTHENTIC1, level, 0);
		Symbol symbol = Symbol.authentic(symbolDetail);

		// when
		int actual = symbol.calculateMinimumDays(4565);

		// then
		assertThat(actual).isEqualTo(expected);
	}

	@DisplayName("세르니움 심볼이 아닌 다른 어센틱심볼의 최소 일수 계산한다")
	@CsvSource(value = {"1,0,457", "2,0,454", "3,0,446", "4,0,432", "5,0,410", "10,0,110", "10,1090,1", "11,0,0"})
	@ParameterizedTest
	void calculateMinimumDaysForOtherAuthentic(int level, int growthCount, int expected) {
		// given
		SymbolDetail symbolDetail = createSymbolDetail(SymbolDetail.Name.AUTHENTIC2, level, growthCount);
		Symbol symbol = Symbol.authentic(symbolDetail);

		// when
		int actual = symbol.calculateMinimumDays(4565);

		// then
		assertThat(actual).isEqualTo(expected);
	}

	private static LocalDate createCriteriaDate() {
		return LocalDate.of(2024, 1, 1);
	}

	private SymbolDetail createSymbolDetail(SymbolDetail.Name name, int level, int growthCount) {
		return SymbolDetail.builder()
			.symbolName(name)
			.symbolLevel(level)
			.symbolGrowthCount(growthCount)
			.build();
	}
}
