package nemo.mestman.domain.symbol;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.ToString;
import nemo.mestman.domain.chracter.service.response.maple.SymbolEquipmentResponse;

@ToString
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class SymbolEquipment {

	private LocalDateTime date;
	private String characterClass;
	private List<SymbolDetail> symbol;

	public static SymbolEquipment from(SymbolEquipmentResponse response) {
		return new SymbolEquipment(
			LocalDateTime.parse(response.getDate(), DateTimeFormatter.ISO_DATE_TIME),
			response.getCharacterClass(),
			response.getSymbol());
	}
}
