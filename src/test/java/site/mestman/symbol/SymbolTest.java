package site.mestman.symbol;

import java.time.LocalDate;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SymbolTest {

	@DisplayName("심볼이 주어지고 해당 심볼이 만렙을 달성하기 위한 필요일자를 계산한다")
	@Test
	void calculateDateForMaxLevel() {
		// given
		Symbol pathOfVanishing = new Symbol();
		int numberOfSymbolPerDay = 20;
		// when
		LocalDate requiredDate = pathOfVanishing.calculateCompletionDateForMaxLevel(numberOfSymbolPerDay);
		// then
		Assertions.assertThat(requiredDate).isEqualTo(LocalDate.now().plusDays(134));
	}
}
