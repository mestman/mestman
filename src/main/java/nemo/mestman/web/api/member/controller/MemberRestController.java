package nemo.mestman.web.api.member.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import nemo.mestman.web.api.member.request.MemberRegisterRequest;
import nemo.mestman.web.api.member.response.MemberRegisterResponse;
import nemo.mestman.web.api.member.service.MemberService;
import nemo.mestman.web.common.ApiResponse;
import nemo.mestman.web.success.MemberSuccessCode;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/members")
public class MemberRestController {

	private final MemberService service;

	@PostMapping
	public ResponseEntity<ApiResponse<MemberRegisterResponse>> registerMember(
		@RequestBody MemberRegisterRequest request) {
		MemberRegisterResponse response = service.registerMember(request);
		return ResponseEntity.ok(
			ApiResponse.success(
				MemberSuccessCode.OK_REGISTER_MEMBER,
				response
			)
		);
	}
}
