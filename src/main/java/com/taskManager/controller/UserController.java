package com.taskManager.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.taskManager.dto.user.UserRequestDto;
import com.taskManager.dto.user.UserResponseDto;
import com.taskManager.response.ApiResponse;
import com.taskManager.response.PageResponse;
import com.taskManager.service.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController
{
	private final UserService us;

	@GetMapping
	public ResponseEntity<ApiResponse<PageResponse<UserResponseDto>>> getAllUsers(
			Pageable pageable)
	{
		Page<UserResponseDto> users = us.findAll(pageable);
		PageResponse<UserResponseDto> metaWrapped = PageResponse.from(users);

		return ResponseEntity
				.ok(ApiResponse.success(metaWrapped, "Fetched paginated users"));
	}

	// GET localhost:8080/api/users/search?query=example
	// GET localhost:8080/api/users/search?query=peter
	// localhost:8080/api/users/search?query=example&page=1&size=1
	@GetMapping("/search")
	public ResponseEntity<ApiResponse<Page<UserResponseDto>>> searchusers(
			@RequestParam String query,
			Pageable pageable)
	{
		Page<UserResponseDto> results = us.searchUsers(query, pageable);

		String msg = results.isEmpty() ? "No users matched your search query"
				: "User search results\n";

		return ResponseEntity.ok(ApiResponse.success(results, msg));
	}

	@PostMapping
	public ResponseEntity<ApiResponse<UserResponseDto>> createUser(
			@Valid @RequestBody UserRequestDto uDto)
	{
		UserResponseDto savedUser = us.saveUser(uDto);

		return ResponseEntity
				.ok(ApiResponse.success(savedUser, "User created successfully"));
	}

}
