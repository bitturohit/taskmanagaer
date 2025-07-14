package com.taskManager.service;

import org.springframework.data.domain.Page;

import com.taskManager.dto.user.UserRequestDto;
import com.taskManager.dto.user.UserResponseDto;

public interface UserService
{
	UserResponseDto saveUser(UserRequestDto userDto);

	Page<UserResponseDto> findAll(int page, int size);

	void deleteById(int id);
}
