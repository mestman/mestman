package nemo.mestman.web.api.chracter.controller.response;

import java.time.LocalDate;
import java.util.List;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Getter
@EqualsAndHashCode(of = {"date", "symbol"})
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SymbolMinDaysResponse {
	private LocalDate date;
	private List<SymbolMinDays> symbol;

	@Builder
	public SymbolMinDaysResponse(LocalDate date, List<SymbolMinDays> symbol) {
		this.date = date;
		this.symbol = symbol;
	}
}

