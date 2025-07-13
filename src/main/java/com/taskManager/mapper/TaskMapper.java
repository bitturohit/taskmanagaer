package com.taskManager.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.taskManager.dto.TaskRequestDto;
import com.taskManager.dto.TaskResponseDto;
import com.taskManager.model.Task;
import com.taskManager.model.User;

@Mapper(componentModel = "spring")
public interface TaskMapper
{
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "createdFor", source = "user")
	Task toEntity(TaskRequestDto dto, User user);

	@Mapping(source = "createdFor.name", target = "createdFor")
	TaskResponseDto toResponse(Task task);
}
