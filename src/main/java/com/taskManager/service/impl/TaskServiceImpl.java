package com.taskManager.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.taskManager.dto.task.TaskRequestDto;
import com.taskManager.dto.task.TaskResponseDto;
import com.taskManager.exception.ResourceNotFound;
import com.taskManager.mapper.TaskMapper;
import com.taskManager.model.Task;
import com.taskManager.model.User;
import com.taskManager.repository.TaskRepository;
import com.taskManager.repository.UserRepository;
import com.taskManager.service.TaskService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService
{
	private final UserRepository ur;
	private final TaskRepository tr;
	private final TaskMapper mapper;

	@Override
	public TaskResponseDto createTask(TaskRequestDto taskDto)
	{
		User user = ur.findById(taskDto.getUserId())
				.orElseThrow(() -> new ResourceNotFound(
						"User not found with id: " + taskDto.getUserId()));

		Task task = mapper.toEntity(taskDto, user);

		Task saved = tr.save(task);

		return mapper.toResponse(saved);
	}

	@Override
	public void deleteTask(long id)
	{
		Task task = tr.findById(id)
				.orElseThrow(() -> new ResourceNotFound("Task not found with id: " + id));

		tr.delete(task);
	}

	@Override
	public Page<TaskResponseDto> getAllTasks(Pageable pageable)
	{
		return tr.findAll(pageable).map(task -> mapper.toResponse(task));
	}

}
