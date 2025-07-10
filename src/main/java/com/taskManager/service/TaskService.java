package com.taskManager.service;

import java.util.List;

import com.taskManager.dto.TaskRequestDto;
import com.taskManager.dto.TaskResponseDto;

public interface TaskService
{
	TaskResponseDto createTask(TaskRequestDto taskDto);

	List<TaskResponseDto> getAllTasks();

	void deleteTask(long id);
}
