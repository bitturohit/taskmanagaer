package com.taskManager.service;

import java.util.List;

import com.taskManager.dto.user.UserRequestDto;
import com.taskManager.dto.user.UserResponseDto;

public interface UserService
{
	UserResponseDto saveUser(UserRequestDto userDto);

	List<UserResponseDto> findAll();

	void deleteById(int id);
}
