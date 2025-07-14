package com.taskManager.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.taskManager.dto.task.AssignedUserDto;
import com.taskManager.dto.task.TaskRequestDto;
import com.taskManager.dto.task.TaskResponseDto;
import com.taskManager.model.Task;
import com.taskManager.model.User;

@Mapper(componentModel = "spring")
public interface TaskMapper
{
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "createdFor", source = "user")
	Task toEntity(TaskRequestDto dto, User user);

	@Mapping(target = "createdFor", expression = "java(mapUser(task.getCreatedFor()))")
	TaskResponseDto toResponse(Task task);

	default AssignedUserDto mapUser(User user)
	{
		if (user == null)
		{
			return null;
		}
		return AssignedUserDto.builder().id(user.getId()).name(user.getName()).build();
	}
}
