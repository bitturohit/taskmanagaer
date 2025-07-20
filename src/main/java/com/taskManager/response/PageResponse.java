package com.taskManager.response;

import java.util.List;

import org.springframework.data.domain.Page;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class PageResponse<T>
{
	private List<T> content;
	private int pageNumber;
	private int pageSize;
	private long totalElements;
	private int totalPages;
	private boolean first;
	private boolean last;
	private boolean hasNext;
	private boolean hasPrevious;

	// Static Factory Method
	public static <T> PageResponse<T> from(Page<T> page)
	{
		return PageResponse.<T>builder()
				.content(page.getContent())
				.pageNumber(page.getNumber())
				.pageSize(page.getSize())
				.totalElements(page.getTotalElements())
				.totalPages(page.getTotalPages())
				.first(page.isFirst())
				.last(page.isLast())
				.hasNext(page.hasNext())
				.hasPrevious(page.hasPrevious())
				.build();
	}
}
