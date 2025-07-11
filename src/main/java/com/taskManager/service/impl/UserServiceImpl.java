package com.taskManager.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.taskManager.dto.UserRequestDto;
import com.taskManager.dto.UserResponseDto;
import com.taskManager.exceptions.ResourceNotFound;
import com.taskManager.mapper.UserMapper;
import com.taskManager.model.Role;
import com.taskManager.model.User;
import com.taskManager.repository.UserRepository;
import com.taskManager.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService
{
	private final UserRepository ur;
	private final UserMapper mapper;

	@Override
	public User saveUser(UserRequestDto userDto)
	{
		User userObj = mapper.toEntity(userDto);
		userObj.setRole(Role.USER);

		return ur.save(userObj);
	}

	@Override
	public List<UserResponseDto> findAll()
	{
		return ur.findAll()
				.stream()
				.map(user -> UserResponseDto.builder()
						.id(user.getId())
						.name(user.getName())
						.email(user.getEmail())
						.role(user.getRole())
						.build())
				.toList();
	}

	@Override
	public void deleteById(int id)
	{
		User u = ur.findById(id)
				.orElseThrow(() -> new ResourceNotFound("Invalid ID: user not found"));
		ur.delete(u);
	}
}
