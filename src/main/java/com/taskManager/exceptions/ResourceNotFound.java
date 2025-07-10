package com.taskManager.exceptions;

public class ResourceNotFound extends RuntimeException
{
	public ResourceNotFound(String message)
	{
		super(message);
	}
}
