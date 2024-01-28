package nemo.mestman.web.api.chracter.service.response.maple;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import nemo.mestman.domain.symbol.SymbolDetail;
import nemo.mestman.web.api.chracter.controller.response.SymbolMinDays;
import nemo.mestman.web.api.chracter.controller.response.SymbolMinDaysResponse;

@Slf4j
@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SymbolEquipmentResponse {
	private String date;
	private String characterClass;
	private List<SymbolDetail> symbol;

	@Builder
	public SymbolEquipmentResponse(String date, String characterClass, List<SymbolDetail> symbol) {
		this.date = date;
		this.characterClass = characterClass;
		this.symbol = symbol;
	}

	public SymbolMinDaysResponse toSymbolMinDaysResponse() {
		return new SymbolMinDaysResponse(
			LocalDate.parse(date, DateTimeFormatter.ISO_DATE_TIME),
			toSymbolMinDays());
	}

	private List<SymbolMinDays> toSymbolMinDays() {
		LocalDate criteriaDate = LocalDate.parse(this.date, DateTimeFormatter.ISO_DATE_TIME);

		return symbol.stream()
			.map(SymbolDetail::toSymbol)
			.map(symbol -> symbol.createSymbolMinimumDays(criteriaDate))
			.toList();
	}
}
