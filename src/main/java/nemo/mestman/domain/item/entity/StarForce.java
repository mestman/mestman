package nemo.mestman.domain.item.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;

@Getter
@Embeddable
public class StarForce {
	private static final int LIMIT = 25;
	@Column(name = "start_force")
	private final int value;

	public static StarForce create(int value) {
		return new StarForce(value);
	}

	private StarForce(int value) {
		if (value < 0 || value > LIMIT) {
			throw new IllegalArgumentException("Invalid star force value: " + value);
		}
		this.value = value;
	}

	protected StarForce() {
		this.value = 0;
	}

}
