package site.mestman.symbol;

import java.time.LocalDate;
import java.util.stream.IntStream;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Symbol {
	private final int level;

	public Symbol(int level) {
		this.level = level;
		if (this.level < 1 || this.level > 20) {
			throw new IllegalArgumentException("The level of the Arkane symbol must be between 1 and 20 levels.");
		}
	}

	// 현재 심볼이 최대 레벨을 달성하기 위해서 필요한 일자를 계산
	public LocalDate calculateCompletionDateForMaxLevel(int numberOfSymbolPerDay) {
		// 만렙 달성 필요 일자 = (만렙 필요 성장치 - 누적 성장치) / 하루에 얻을 수 있는 심볼 개수
		// 누적 성장치 = 현재 레벨 누적 성장치 + 현재 레벨 성장치
		int requiredMaxLevelGrowth = 2679;
		int growthByCurrentLevel = 0;
		int reduceGrowth = reduceGrowthBy(level) + growthByCurrentLevel;
		log.info("reduceGrowth is {} by level", reduceGrowth);
		int days = (requiredMaxLevelGrowth - reduceGrowth) / numberOfSymbolPerDay;
		if (requiredMaxLevelGrowth % numberOfSymbolPerDay != 0) {
			days++;
		}
		log.info("days is {}", days);
		return LocalDate.now().plusDays(days);
	}

	// 현재 레벨에 따른 누적 성장치 계산
	private int reduceGrowthBy(int level) {
		int[] requiredGrowthByLevel = {0, 12, 15, 20, 27, 36, 47, 60, 75, 92, 111, 132, 155, 180, 207, 236, 267, 300,
			335, 372, 0};

		return IntStream.rangeClosed(1, level - 1)
			.map(i -> requiredGrowthByLevel[i])
			.sum();
	}
}
