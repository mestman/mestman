package nemo.mestman.domain.item.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import nemo.mestman.common.api.ApiResponse;
import nemo.mestman.domain.item.dto.request.MapleItemRegisterRequest;
import nemo.mestman.domain.item.dto.response.MapleItemRegisterResponse;
import nemo.mestman.domain.item.service.MapleItemService;
import nemo.mestman.domain.item.successcode.MapleItemSuccessCode;

@RestController
@RequestMapping("/api/items")
@RequiredArgsConstructor
public class MapleItemRestController {

	private final MapleItemService service;

	@PostMapping
	public ResponseEntity<ApiResponse<MapleItemRegisterResponse>> registerItem(
		@Valid @RequestBody MapleItemRegisterRequest request) {
		MapleItemRegisterResponse response = service.registerItem(request);
		return ResponseEntity.ok(ApiResponse.from(MapleItemSuccessCode.REGISTER_ITEM, response));
	}

	@GetMapping
	public ResponseEntity<ApiResponse<Void>> readItems() {
		return ResponseEntity.ok(ApiResponse.from(MapleItemSuccessCode.READ_ITEM));
	}

	@PutMapping
	public ResponseEntity<ApiResponse<Void>> updateItem() {
		return ResponseEntity.ok(ApiResponse.from(MapleItemSuccessCode.UPDATE_ITEM));
	}

	@DeleteMapping
	public ResponseEntity<ApiResponse<Void>> deleteItem() {
		return ResponseEntity.ok(ApiResponse.from(MapleItemSuccessCode.DELETE_ITEM));
	}
}
