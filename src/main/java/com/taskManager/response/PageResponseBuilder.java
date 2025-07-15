package com.taskManager.response;

import org.springframework.data.domain.Page;

public class PageResponseBuilder
{
	// Static Factory Method
	public static <T> PageResponse<T> build(Page<T> page)
	{
		return new PageResponse<>(
				page.getContent(),
				page.getNumber(),
				page.getSize(),
				page.getTotalElements(),
				page.getTotalPages(),
				page.isFirst(),
				page.isLast(),
				page.hasNext(),
				page.hasPrevious());
	}
}
