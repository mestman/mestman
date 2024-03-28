package nemo.mestman.domain.member.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.RequiredArgsConstructor;
import nemo.mestman.common.validator.Validator;
import nemo.mestman.domain.member.dto.request.MemberRegisterRequest;
import nemo.mestman.domain.member.policy.MemberSignUpPolicy;
import nemo.mestman.domain.member.policy.validator.EmailDuplicateValidator;
import nemo.mestman.domain.member.policy.validator.PasswordValidator;
import nemo.mestman.domain.member.repository.MemberRepository;

@Configuration
@RequiredArgsConstructor
public class MemberConfig {

	@Bean
	public MemberSignUpPolicy memberSignUpPolicy(MemberRepository memberRepository) {
		List<Validator<MemberRegisterRequest>> validators = List.of(
			new PasswordValidator(),
			new EmailDuplicateValidator(memberRepository)
		);
		return new MemberSignUpPolicy(validators);
	}
}
