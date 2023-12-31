package nemo.mestman.web.api.chracter.controller.response;

import java.time.LocalDate;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import nemo.mestman.domain.symbol.SymbolDetail;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class SymbolMinimumDays {
	private SymbolDetail.Name name;
	private String symbolIcon; // 심볼 아이콘 이미지 링크
	private Integer symbolLevel; // 현재 레벨
	private Integer symbolGrowthCount; // 현재 레벨의 성장치
	private Integer requiredTotalSymbolCount; // 만렙에 필요한 전체 심볼 개수
	private LocalDate minimumDate;

	public static SymbolMinimumDays of(SymbolDetail symbolDetail, Integer requiredTotalSymbolCount,
		LocalDate minimumDate) {
		return new SymbolMinimumDays(
			symbolDetail.getSymbolName(),
			symbolDetail.getSymbolIcon(),
			symbolDetail.getSymbolLevel(),
			symbolDetail.getSymbolGrowthCount(),
			requiredTotalSymbolCount,
			minimumDate
		);
	}
}
