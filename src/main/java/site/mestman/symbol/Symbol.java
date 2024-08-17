package site.mestman.symbol;

import java.time.LocalDate;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Symbol {

	// 현재 심볼이 최대 레벨을 달성하기 위해서 필요한 일자를 계산
	public LocalDate calculateCompletionDateForMaxLevel(int numberOfSymbolPerDay) {
		// 만렙 달성 필요 일자 = (만렙 필요 성장치 - 누적 성장치) / 하루에 얻을 수 있는 심볼 개수
		// 누적 성장치 = 현재 레벨 누적 성장치 + 현재 레벨 성장치
		int requiredMaxLevelGrowth = 2679;
		int reduceGrowth = 0;
		int days = (requiredMaxLevelGrowth - reduceGrowth) / numberOfSymbolPerDay;
		if (requiredMaxLevelGrowth % numberOfSymbolPerDay != 0) {
			days++;
		}
		log.info("days is {}", days);
		return LocalDate.now().plusDays(days);
	}
}
