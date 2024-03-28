package nemo.mestman.web.api.member.request;

import jakarta.validation.constraints.NotBlank;
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
public class MemberRegisterRequest {
	@NotBlank(message = "필수 정보입니다")
	private String email;
	@NotBlank(message = "필수 정보입니다")
	private String nickname;
	@NotBlank(message = "필수 정보입니다")
	private String password;
	@NotBlank(message = "필수 정보입니다")
	private String passwordConfirm;

	public Member toEntity() {
		return Member.create(email, nickname, password);
	}
}
