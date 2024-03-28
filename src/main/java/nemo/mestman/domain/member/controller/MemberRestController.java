package nemo.mestman.domain.member.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import nemo.mestman.common.api.ApiResponse;
import nemo.mestman.domain.member.dto.request.MemberRegisterRequest;
import nemo.mestman.domain.member.dto.response.MemberRegisterResponse;
import nemo.mestman.domain.member.service.MemberService;
import nemo.mestman.domain.member.successcode.MemberSuccessCode;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/members")
public class MemberRestController {

	private final MemberService service;

	@PostMapping
	public ResponseEntity<ApiResponse<MemberRegisterResponse>> registerMember(
		@Valid @RequestBody MemberRegisterRequest request) {
		MemberRegisterResponse response = service.registerMember(request);
		return ResponseEntity.ok(
			ApiResponse.from(
				MemberSuccessCode.OK_REGISTER_MEMBER,
				response
			)
		);
	}
}
