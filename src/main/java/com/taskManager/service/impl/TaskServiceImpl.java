package com.taskManager.service.impl;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.taskManager.dto.task.TaskFilterDto;
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

	@Override
	public Page<TaskResponseDto> filterTasks(TaskFilterDto filterDto, Pageable pageable)
	{
		System.out.println("Filter DTO → message: " + filterDto.getMessage());
		System.out.println("Filter DTO → createdFor: " + filterDto.getCreatedFor());

		String message = filterDto.getMessage();
		String user = filterDto.getCreatedFor();

		List<Task> filteredTasks = tr.findAll().stream().filter((task) -> {
			boolean matchesMessage = (message == null || message.isBlank())
					|| task.getMessage().toLowerCase().contains(message.toLowerCase());
			boolean matchesUser = (user == null || user.isBlank()) || task.getCreatedFor()
					.getName()
					.trim()
					.toLowerCase()
					.contains(user.toLowerCase());

			return matchesMessage && matchesUser;
		}).toList();

		List<TaskResponseDto> filteredTaskResponseDtos = filteredTasks.stream()
				.map(mapper::toResponse)
				.toList();

		// pageable.getOffset() returns the starting index in the full list based on the
		// current page.
		int start = (int) pageable.getOffset();
		int end = Math.min(start + pageable.getPageSize(),
				filteredTaskResponseDtos.size());

		List<TaskResponseDto> pagedList = filteredTaskResponseDtos.subList(start, end);

		return new PageImpl<>(pagedList, pageable, filteredTaskResponseDtos.size());
	}

}
