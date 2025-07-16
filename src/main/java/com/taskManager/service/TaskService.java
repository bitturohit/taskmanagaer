package com.taskManager.service;

import java.util.List;

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

	List<TaskResponseDto> filterTasks(TaskFilterDto dto);
}
