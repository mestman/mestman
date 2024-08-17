package site.mestman.symbol;

import static org.assertj.core.api.Assertions.*;

import java.time.LocalDate;
import java.util.stream.Stream;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

class SymbolTest {

	@DisplayName("아케인 심볼이 주어지고 해당 심볼이 만렙을 달성하기 위한 필요일자를 계산한다")
	@MethodSource(value = {"acaneSymbolSource"})
	@ParameterizedTest
	void testCalculateDateForMaxLevel(int level, int growthForCurrentLevel, LocalDate expected) {
		// given
		Symbol pathOfVanishing = Symbol.arcane(level, growthForCurrentLevel);
		int numberOfSymbolPerDay = 20;
		// when
		LocalDate completionDate = pathOfVanishing.calculateCompletionDateForMaxLevel(numberOfSymbolPerDay);
		// then
		assertThat(completionDate).isEqualTo(expected);
	}

	public static Stream<Arguments> acaneSymbolSource() {
		LocalDate today = LocalDate.now();
		return Stream.of(
			Arguments.of(1, 1, today.plusDays(134)),
			Arguments.of(2, 8, today.plusDays(133)),
			Arguments.of(10, 1, today.plusDays(115)),
			Arguments.of(19, 1, today.plusDays(19)),
			Arguments.of(19, 371, today.plusDays(1)),
			Arguments.of(19, 372, today.plusDays(0)),
			Arguments.of(20, 0, today.plusDays(0))
		);
	}

	@DisplayName("아케인 심볼의 레벨은 1~20 사이가 아니면 예외가 발생한다")
	@CsvSource(value = {"-1", "0", "21"})
	@ParameterizedTest
	void testCreateSymbolInstanceForLevel(int level) {
		// given

		// when
		Throwable throwable = catchThrowable(() -> Symbol.arcane(level));
		// then
		assertThat(throwable)
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage("The level of the Acane symbol must be between 1 and 20 levels.");
	}

	@DisplayName("현재 레벨 성장치는 0보다 작거나 아케인 심볼의 최대 누적 성장치보다 크면 예외가 발생한다")
	@CsvSource(value = {"-1", "0", "2680"})
	@ParameterizedTest
	void testCreateSymbolInstanceForGrowthForCurrentLevel(int growthForCurrentLevel) {
		// given
		int level = 1;
		// when
		Throwable throwable = catchThrowable(() -> Symbol.arcane(level, growthForCurrentLevel));
		// then
		Assertions.assertThat(throwable)
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage("The growth for the current level of the Acane symbol must be between 1 and 2679.");
	}

	@DisplayName("아케인 심볼이 최대 레벨(20)일때 현재 성장치는 0이어야 한다")
	@Test
	void testArcaneSymbolGrowthAtMaxLevel() {
		// given
		int level = 20;
		// when
		Symbol arcane = Symbol.arcane(level, 0);
		// then
		int expected = 0;
		Assertions.assertThat(arcane)
			.extracting("growthForCurrentLevel")
			.isEqualTo(expected);
	}

	@DisplayName("아케인 심볼이 최대 레벨(20)일때 현재 성장치는 0이 아닌 경우 예외가 발생한다")
	@CsvSource(value = {"1", "2679"})
	@ParameterizedTest
	void testArcaneSymbolThrowsExceptionWhenGrowthNotEqualsMaxLevel(int growthForCurrentLevel) {
		// given
		int level = 20;
		// when
		Throwable throwable = catchThrowable(() -> Symbol.arcane(level, growthForCurrentLevel));
		// then
		Assertions.assertThat(throwable)
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage("when the Arcane Symbol's max level is 20, growthForCurrentLevel must be 0.");
	}

	@DisplayName("아케인 심볼의 요구되는 최대 레벨 누적 성장치는 2,679이여야 한다")
	@Test
	void testMaxGrowthForCurrentLevel() {
		// given
		int level = 1;
		// when
		Symbol symbol = Symbol.arcane(level);
		// then
		int expected = 2679;
		assertThat(symbol)
			.extracting("requiredMaxLevelGrowth")
			.isEqualTo(expected);
	}

	@DisplayName("어센틱 세르니움 심볼이 주어지고 해당 심볼이 만렙을 달성하기 위한 필요일자를 계산한다")
	@MethodSource(value = {"cerniumAuthenticSymbolSource"})
	@ParameterizedTest
	void testCalculateDateForMaxLevelWithAuthentic(int level, int growthForCurrentLevel, int numberOfSymbolPerDay,
		LocalDate expected) {
		// given
		Symbol pathOfVanishing = Symbol.cernium(level, growthForCurrentLevel);
		// when
		LocalDate completionDate = pathOfVanishing.calculateCompletionDateForMaxLevel(numberOfSymbolPerDay);
		// then
		assertThat(completionDate).isEqualTo(expected);
	}

	public static Stream<Arguments> cerniumAuthenticSymbolSource() {
		LocalDate today = LocalDate.now();
		int numberOfSymbolPerDay = 20;
		return Stream.of(
			Arguments.of(1, 1, numberOfSymbolPerDay, today.plusDays(229)),
			Arguments.of(2, 12, numberOfSymbolPerDay, today.plusDays(227)),
			Arguments.of(10, 1, numberOfSymbolPerDay, today.plusDays(55)),
			Arguments.of(10, 1109, numberOfSymbolPerDay, today.plusDays(1)),
			Arguments.of(10, 1100, numberOfSymbolPerDay, today.plusDays(0)),
			Arguments.of(11, 0, numberOfSymbolPerDay, today.plusDays(0))
		);
	}

	@DisplayName("어센틱 세르니움 심볼이 아닌 다른 어센틱 심볼이 주어지고 해당 심볼이 만렙을 달성하기 위한 필요일자를 계산한다")
	@MethodSource(value = {"otherAuthenticSymbolSource"})
	@ParameterizedTest
	void testCalculateDateForMaxLevelWithOtherAuthentic(int level, int growthForCurrentLevel, int numberOfSymbolPerDay,
		LocalDate expected) {
		// given
		Symbol pathOfVanishing = Symbol.basic(level, growthForCurrentLevel);
		// when
		LocalDate completionDate = pathOfVanishing.calculateCompletionDateForMaxLevel(numberOfSymbolPerDay);
		// then
		assertThat(completionDate).isEqualTo(expected);
	}

	private static Stream<Arguments> otherAuthenticSymbolSource() {
		LocalDate today = LocalDate.now();
		int numberOfSymbolPerDay = 10;
		return Stream.of(
			Arguments.of(1, 1, numberOfSymbolPerDay, today.plusDays(457)),
			Arguments.of(2, 12, numberOfSymbolPerDay, today.plusDays(453)),
			Arguments.of(10, 1, numberOfSymbolPerDay, today.plusDays(110)),
			Arguments.of(10, 1109, numberOfSymbolPerDay, today.plusDays(1)),
			Arguments.of(10, 1100, numberOfSymbolPerDay, today.plusDays(0)),
			Arguments.of(11, 0, numberOfSymbolPerDay, today.plusDays(0))
		);
	}

	@DisplayName("어센틱 심볼의 레벨은 1~11 사이가 아니면 예외가 발생한다")
	@CsvSource(value = {"-1", "0", "12"})
	@ParameterizedTest
	void testCreateSymbolInstanceForLevelWithAuthentic(int level) {
		// given
		int growthForCurrentLevel = 0;
		// when
		Throwable throwable = catchThrowable(() -> Symbol.cernium(level, growthForCurrentLevel));
		// then
		assertThat(throwable)
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage("The level of the Authentic symbol must be between 1 and 11 levels.");
	}

	@DisplayName("어센틱 심볼의 현재 레벨 성장치는 0보다 작거나 어센틱 심볼의 최대 누적 성장치보다 크면 예외가 발생한다")
	@CsvSource(value = {"-1", "0", "4566"})
	@ParameterizedTest
	void testCreateSymbolInstanceForGrowthForCurrentLevelWithAuthentic(int growthForCurrentLevel) {
		// given
		int level = 1;
		// when
		Throwable throwable = catchThrowable(() -> Symbol.cernium(level, growthForCurrentLevel));
		// then
		Assertions.assertThat(throwable)
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage("The growth for the current level of the Authentic symbol must be between 1 and 4565.");
	}

	@DisplayName("어센틱 심볼이 최대 레벨(11)일때 현재 성장치는 0이어야 한다")
	@Test
	void testAuthenticSymbolGrowthAtMaxLevel() {
		// given
		int level = 11;
		int growthForCurrentLevel = 0;
		// when
		Symbol authentic = Symbol.cernium(level, growthForCurrentLevel);
		// then
		int expected = 0;
		Assertions.assertThat(authentic)
			.extracting("growthForCurrentLevel")
			.isEqualTo(expected);
	}

	@DisplayName("어센틱 심볼이 최대 레벨(11)일때 현재 성장치는 0이 아닌 경우 예외가 발생한다")
	@CsvSource(value = {"1", "4565"})
	@ParameterizedTest
	void testAuthenticSymbolThrowsExceptionWhenGrowthNotEqualsMaxLevel(int growthForCurrentLevel) {
		// given
		int level = 11;
		// when
		Throwable throwable = catchThrowable(() -> Symbol.cernium(level, growthForCurrentLevel));
		// then
		Assertions.assertThat(throwable)
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage("when the Authentic Symbol's max level is 11, growthForCurrentLevel must be 0.");
	}

	@DisplayName("어센틱 심볼의 최대 레벨 누적 성장치는 4,565이여야 한다")
	@Test
	void testMaxGrowthForCurrentLevelWithAuthentic() {
		// given
		int level = 1;
		int growthForCurrentLevel = 1;
		// when
		Symbol symbol = Symbol.cernium(level, growthForCurrentLevel);
		// then
		int expected = 4565;
		assertThat(symbol)
			.extracting("requiredMaxLevelGrowth")
			.isEqualTo(expected);
	}
}
