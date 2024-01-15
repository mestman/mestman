package nemo.mestman.domain.symbol;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SymbolDetail {
	private Name symbolName;
	private Integer symbolLevel;
	private Integer symbolGrowthCount;

	@Builder
	public SymbolDetail(Name symbolName, Integer symbolLevel, Integer symbolGrowthCount) {
		this.symbolName = symbolName;
		this.symbolLevel = symbolLevel;
		this.symbolGrowthCount = symbolGrowthCount;
	}

	public Symbol toSymbol() {
		if (isAcane()) {
			return Symbol.acane(this);
		}
		return Symbol.authentic(this);
	}

	public enum Name {
		ACANE1(
			"아케인심볼 : 소멸의 여로",
			"https://open.api.nexon.com/static/maplestory/ItemIcon/KEIDJHOA.png"
		),
		ACANE2(
			"아케인심볼 : 츄츄 아일랜드",
			"https://open.api.nexon.com/static/maplestory/ItemIcon/KEIDJHOD.png"
		),
		ACANE3(
			"아케인심볼 : 레헬른",
			"https://open.api.nexon.com/static/maplestory/ItemIcon/KEIDJHOC.png"
		),
		ACANE4(
			"아케인심볼 : 아르카나",
			"https://open.api.nexon.com/static/maplestory/ItemIcon/KEIDJHOF.png"
		),
		ACANE5(
			"아케인심볼 : 모라스",
			"https://open.api.nexon.com/static/maplestory/ItemIcon/KEIDJHOH.png"
		),
		ACANE6(
			"아케인심볼 : 에스페라",
			"https://open.api.nexon.com/static/maplestory/ItemIcon/KEIDJHOE.png"
		),
		AUTHENTIC1(
			"어센틱심볼 : 세르니움",
			"https://open.api.nexon.com/static/maplestory/ItemIcon/KEIDIHOB.png"
		),
		AUTHENTIC2(
			"어센틱심볼 : 아르크스",
			"https://open.api.nexon.com/static/maplestory/ItemIcon/KEIDIHOA.png"
		),
		AUTHENTIC3("어센틱심볼 : 오디움",
			"https://open.api.nexon.com/static/maplestory/ItemIcon/KEIDIHOD.png"
		),
		AUTHENTIC4(
			"어센틱심볼 : 도원경",
			"https://open.api.nexon.com/static/maplestory/ItemIcon/KEIDIHOC.png"
		),
		AUTHENTIC5(
			"어센틱심볼 : 아르테리아",
			"https://open.api.nexon.com/static/maplestory/ItemIcon/KEIDIHOF.png"
		),
		AUTHENTIC6(
			"어센틱심볼 : 카르시온",
			"https://open.api.nexon.com/static/maplestory/ItemIcon/KEIDIHOE.png"
		);

		private final String symbolName;
		@Getter
		private final String symbolIcon;

		Name(String symbolName, String symbolIcon) {
			this.symbolName = symbolName;
			this.symbolIcon = symbolIcon;
		}

		@JsonCreator(mode = JsonCreator.Mode.DELEGATING)
		public static Name forValues(@JsonProperty("symbol_name") String symbolName) {
			for (Name name : Name.values()) {
				if (name.symbolName.equals(symbolName)) {
					return name;
				}
			}
			throw new IllegalArgumentException("심볼 이름을 생성할 수 없습니다. symbolName=" + symbolName);
		}

		public boolean isCernium() {
			return this == AUTHENTIC1;
		}

		public boolean isAcane() {
			return this == ACANE1 ||
				this == ACANE2 ||
				this == ACANE3 ||
				this == ACANE4 ||
				this == ACANE5 ||
				this == ACANE6;
		}

		public boolean isAuthentic() {
			return this == AUTHENTIC1 ||
				this == AUTHENTIC2 ||
				this == AUTHENTIC3 ||
				this == AUTHENTIC4 ||
				this == AUTHENTIC5 ||
				this == AUTHENTIC6;
		}

		@JsonValue
		public String getSymbolName() {
			return symbolName;
		}
	}

	public boolean isAcane() {
		return symbolName.isAcane();
	}

	public boolean isAuthentic() {
		return symbolName.isAuthentic();
	}

	public boolean isCernium() {
		return symbolName.isCernium();
	}
}
