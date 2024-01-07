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
	private String symbolIcon;
	private Integer symbolLevel;
	private Integer symbolGrowthCount;

	@Builder
	public SymbolDetail(Name symbolName, String symbolIcon, Integer symbolLevel, Integer symbolGrowthCount) {
		this.symbolName = symbolName;
		this.symbolIcon = symbolIcon;
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
		ACANE1("아케인심볼 : 소멸의 여로"),
		ACANE2("아케인심볼 : 츄츄 아일랜드"),
		ACANE3("아케인심볼 : 레헬른"),
		ACANE4("아케인심볼 : 아르카나"),
		ACANE5("아케인심볼 : 모라스"),
		ACANE6("아케인심볼 : 에스페라"),
		AUTHENTIC1("어센틱심볼 : 세르니움"),
		AUTHENTIC2("어센틱심볼 : 아르크스"),
		AUTHENTIC3("어센틱심볼 : 오디움"),
		AUTHENTIC4("어센틱심볼 : 도원경"),
		AUTHENTIC5("어센틱심볼 : 아르테리아"),
		AUTHENTIC6("어센틱심볼 : 카르시온");

		private final String symbolName;

		Name(String symbolName) {
			this.symbolName = symbolName;
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
