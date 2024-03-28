package nemo.mestman.domain.member.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import nemo.mestman.domain.member.entity.Member;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
public class MemberRegisterResponse {
	private Long id;
	private String email;
	private String nickname;

	public static MemberRegisterResponse from(Member member) {
		return new MemberRegisterResponse(member.getId(), member.getEmail(), member.getNickname());
	}
}
