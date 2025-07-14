package com.taskManager.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.taskManager.dto.user.UserRequestDto;
import com.taskManager.dto.user.UserResponseDto;

public interface UserService
{
	UserResponseDto saveUser(UserRequestDto userDto);

	Page<UserResponseDto> findAll(Pageable pageable);

	void deleteById(int id);
}
