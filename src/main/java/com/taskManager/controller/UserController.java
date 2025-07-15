package com.taskManager.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.taskManager.dto.user.UserResponseDto;
import com.taskManager.response.ApiResponse;
import com.taskManager.service.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController
{
	private final UserService us;

	@PostMapping
	public ResponseEntity<ApiResponse<UserResponseDto>> createUser(
			@Valid @RequestBody com.taskManager.dto.user.UserRequestDto uDto)
	{
		UserResponseDto savedUser = us.saveUser(uDto);

		return ResponseEntity.status(HttpStatus.CREATED)
				.body(new ApiResponse<>(true, "User created successfully", savedUser));
	}

	@GetMapping
	public ResponseEntity<ApiResponse<Page<UserResponseDto>>> getAllUsers(Pageable pageable)
	{
		Page<UserResponseDto> users = us.findAll(pageable);

		return ResponseEntity.ok(new ApiResponse<>(true, "Fetched paginated users", users));
	}

}
