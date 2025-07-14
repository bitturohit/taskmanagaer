package com.taskManager.mapper;

import org.mapstruct.Mapper;

import com.taskManager.dto.user.UserRequestDto;
import com.taskManager.dto.user.UserResponseDto;
import com.taskManager.model.User;

@Mapper(componentModel = "spring")
public interface UserMapper
{
	User toEntity(UserRequestDto dto);

	UserResponseDto toResponse(User user);
}
