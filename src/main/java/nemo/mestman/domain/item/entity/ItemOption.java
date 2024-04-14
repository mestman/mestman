package nemo.mestman.domain.item.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class ItemOption {
	private OptionType name;
	private int value;

	public static ItemOption byType(OptionType name, int value) {
		return new ItemOption(name, value);
	}

	public static ItemOption str(int value) {
		return new ItemOption(OptionType.STR, value);
	}

	public static ItemOption dex(int value) {
		return new ItemOption(OptionType.DEX, value);
	}

	/**
	 * 아이템 옵션 파싱
	 * @return format: name:value ex) STR:10
	 */
	public String parse() {
		return String.format("%s=%d", name, value);
	}

	public enum OptionType {
		STR, DEX, INT, LUK, HP, ATT, DEF;
	}
}
