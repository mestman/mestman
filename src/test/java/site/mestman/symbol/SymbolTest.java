package site.mestman.symbol;

import static org.assertj.core.api.Assertions.*;

import java.time.LocalDate;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

class SymbolTest {

	@DisplayName("심볼이 주어지고 해당 심볼이 만렙을 달성하기 위한 필요일자를 계산한다")
	@MethodSource(value = {"symbolSource"})
	@ParameterizedTest
	void testCalculateDateForMaxLevel(int level, int growthForCurrentLevel, LocalDate expected) {
		// given
		Symbol pathOfVanishing = new Symbol(level, growthForCurrentLevel);
		int numberOfSymbolPerDay = 20;
		// when
		LocalDate completionDate = pathOfVanishing.calculateCompletionDateForMaxLevel(numberOfSymbolPerDay);
		// then
		assertThat(completionDate).isEqualTo(expected);
	}

	public static Stream<Arguments> symbolSource() {
		LocalDate today = LocalDate.now();
		return Stream.of(
			Arguments.of(1, 0, today.plusDays(134)),
			Arguments.of(2, 8, today.plusDays(133)),
			Arguments.of(10, 0, today.plusDays(115))
		);
	}

	@DisplayName("아케인 심볼의 레벨은 1~20 사이여야 한다")
	@CsvSource(value = {"-1", "0", "21"})
	@ParameterizedTest
	void testCreateSymbolInstance(int level) {
		// given
		int growthForCurrentLevel = 0;
		// when
		Throwable throwable = catchThrowable(() -> new Symbol(level, growthForCurrentLevel));
		// then
		assertThat(throwable)
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage("The level of the Arkane symbol must be between 1 and 20 levels.");
	}
}
