package com.taskManager.service;

import java.util.List;

import com.taskManager.dto.task.TaskRequestDto;
import com.taskManager.dto.task.TaskResponseDto;

public interface TaskService
{
	TaskResponseDto createTask(TaskRequestDto taskDto);

	List<TaskResponseDto> getAllTasks();

	void deleteTask(long id);
}
