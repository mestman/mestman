package nemo.mestman.web.api.member.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import nemo.mestman.domain.member.Member;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
public class MemberRegisterResponse {
	private Long id;

	public static MemberRegisterResponse from(Member member) {
		return new MemberRegisterResponse(member.getId());
	}
}
