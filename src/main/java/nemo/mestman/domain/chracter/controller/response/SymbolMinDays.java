package nemo.mestman.domain.chracter.controller.response;

import java.time.LocalDate;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import nemo.mestman.domain.symbol.SymbolDetail;

@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class SymbolMinDays {
	private SymbolDetail.Name name;
	private String symbolIcon; // 심볼 아이콘 이미지 링크
	private Integer symbolLevel; // 현재 레벨
	private Integer symbolGrowthCount; // 현재 레벨의 성장치
	private Integer requiredTotalSymbolCount; // 만렙에 필요한 전체 심볼 개수
	private LocalDate minimumDate;

	public static SymbolMinDays of(SymbolDetail symbolDetail, Integer requiredTotalSymbolCount,
		LocalDate minimumDate) {
		return new SymbolMinDays(
			symbolDetail.getSymbolName(),
			symbolDetail.getSymbolName().getSymbolIcon(),
			symbolDetail.getSymbolLevel(),
			symbolDetail.getSymbolGrowthCount(),
			requiredTotalSymbolCount,
			minimumDate
		);
	}
}
