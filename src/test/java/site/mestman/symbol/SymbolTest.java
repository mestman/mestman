package site.mestman.symbol;

import java.time.LocalDate;
import java.util.stream.Stream;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class SymbolTest {

	@DisplayName("심볼이 주어지고 해당 심볼이 만렙을 달성하기 위한 필요일자를 계산한다")
	@MethodSource(value = {"symbolSource"})
	@ParameterizedTest
	void calculateDateForMaxLevel(int level, LocalDate expected) {
		// given
		Symbol pathOfVanishing = new Symbol(level);
		int numberOfSymbolPerDay = 20;
		// when
		LocalDate completionDate = pathOfVanishing.calculateCompletionDateForMaxLevel(numberOfSymbolPerDay);
		// then
		Assertions.assertThat(completionDate).isEqualTo(expected);
	}

	public static Stream<Arguments> symbolSource() {
		LocalDate today = LocalDate.now();
		return Stream.of(
			Arguments.of(1, today.plusDays(134)),
			Arguments.of(10, today.plusDays(115))
		);
	}
}
