package com.taskManager.service;

import java.util.List;

import com.taskManager.dto.UserRequestDto;
import com.taskManager.dto.UserResponseDto;
import com.taskManager.model.User;

public interface UserService
{
	User saveUser(UserRequestDto userDto);

	List<UserResponseDto> findAll();

	void deleteById(int id);
}
