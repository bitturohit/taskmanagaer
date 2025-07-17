package com.taskManager.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.taskManager.dto.task.TaskFilterDto;
import com.taskManager.dto.task.TaskRequestDto;
import com.taskManager.dto.task.TaskResponseDto;

public interface TaskService
{
	TaskResponseDto createTask(TaskRequestDto taskDto);

	Page<TaskResponseDto> getAllTasks(Pageable pageable);

	void deleteTask(long id);

	Page<TaskResponseDto> filterTasks(TaskFilterDto filterDto, Pageable pageable);
}
