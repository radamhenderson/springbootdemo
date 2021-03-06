package com.application.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(value=HttpStatus.NOT_FOUND,reason="This person is not found in the system")
public class PersonNotFound extends Exception
{
	private static final long serialVersionUID = 100L;
}