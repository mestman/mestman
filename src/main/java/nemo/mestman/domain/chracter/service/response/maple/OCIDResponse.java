package nemo.mestman.domain.chracter.service.response.maple;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@NoArgsConstructor
@Getter
public class OCIDResponse {
	private String ocid;

	@Builder
	public OCIDResponse(String ocid) {
		this.ocid = ocid;
	}
}
