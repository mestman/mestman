package nemo.mestman.web.api.chracter.service.response.maple;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import nemo.mestman.domain.symbol.SymbolDetail;
import nemo.mestman.web.api.chracter.controller.response.CharacterSymbolMinimumDaysResponse;
import nemo.mestman.web.api.chracter.controller.response.SymbolMinimumDays;

@Slf4j
@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SymbolEquipmentResponse {
	private String date;
	private String characterClass;
	private List<SymbolDetail> symbol;

	public CharacterSymbolMinimumDaysResponse createCharacterSymbolMinimumDaysResponse() {
		return new CharacterSymbolMinimumDaysResponse(
			toLocalDate(),
			toSymbolMinimumDays());
	}

	private List<SymbolMinimumDays> toSymbolMinimumDays() {
		LocalDate criteriaDate = LocalDate.parse(this.date, DateTimeFormatter.ISO_DATE_TIME);

		return symbol.stream()
			.map(SymbolDetail::toSymbol)
			.map(symbol -> symbol.createSymbolMinimumDays(criteriaDate))
			.toList();
	}

	private LocalDate toLocalDate() {
		return LocalDate.parse(date, DateTimeFormatter.ISO_DATE_TIME);
	}
}
