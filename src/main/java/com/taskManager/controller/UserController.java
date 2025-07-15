package com.taskManager.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.taskManager.dto.user.UserResponseDto;
import com.taskManager.response.ApiResponse;
import com.taskManager.response.PageResponse;
import com.taskManager.response.PageResponseBuilder;
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
		PageResponse<UserResponseDto> metaWrapped = PageResponseBuilder
				.build(users);
		return ResponseEntity.ok(new ApiResponse<>(
				true,
				"Fetched paginated users",
				metaWrapped));
	}

	@GetMapping("/search")
	public ResponseEntity<ApiResponse<List<UserResponseDto>>> searchusers(
			@RequestParam String query)
	{
		List<UserResponseDto> results = us.searchUsers(query);
		return ResponseEntity
				.ok(new ApiResponse<>(true, "User search results", results));
	}

	@PostMapping
	public ResponseEntity<ApiResponse<UserResponseDto>> createUser(
			@Valid @RequestBody com.taskManager.dto.user.UserRequestDto uDto)
	{
		UserResponseDto savedUser = us.saveUser(uDto);
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(new ApiResponse<>(
						true,
						"User created successfully",
						savedUser));
	}

}
