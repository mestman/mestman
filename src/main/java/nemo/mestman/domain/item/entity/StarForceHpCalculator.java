package nemo.mestman.domain.item.entity;

import java.util.stream.IntStream;

public class StarForceHpCalculator implements StarForceCalculator {
	private final int[] values = {5, 5, 5, 10, 10, 15, 15, 20, 20, 25, 25, 25, 25, 25, 25, 0, 0, 0, 0, 0, 0, 0, 0, 0,
		0};

	@Override
	public int calculate(int starForce, int level) {
		return IntStream.rangeClosed(1, starForce)
			.map(i -> values[i - 1])
			.sum();
	}
}
