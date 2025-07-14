package com.taskManager.exception;

public class ResourceNotFound extends RuntimeException
{
	public ResourceNotFound(String message)
	{
		super(message);
	}
}
