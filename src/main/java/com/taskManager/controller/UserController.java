package com.taskManager.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.taskManager.dto.user.UserResponseDto;
import com.taskManager.response.ResponseApi;
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
	public ResponseEntity<ResponseApi<UserResponseDto>> createUser(
			@Valid @RequestBody com.taskManager.dto.user.UserRequestDto uDto)
	{
		UserResponseDto savedUser = us.saveUser(uDto);

		return ResponseEntity.status(HttpStatus.CREATED)
				.body(new ResponseApi<>("User created successfully", savedUser, true));
	}

	@GetMapping
	public ResponseEntity<ResponseApi<List<UserResponseDto>>> getAllUsers()
	{
		List<UserResponseDto> users = us.findAll();

		return ResponseEntity.ok(new ResponseApi<>("Fetched all users", users, true));
	}

}
