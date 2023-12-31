package nemo.mestman.web.api.chracter.controller.response;

import java.time.LocalDate;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
@AllArgsConstructor
public class CharacterSymbolMinimumDaysResponse {
	private LocalDate date;
	private List<SymbolMinimumDays> symbol;
}
