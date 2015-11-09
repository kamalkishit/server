package com.humanize.server.common;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.humanize.server.authentication.exception.NullEmailIdException;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(NullEmailIdException.class)
	@ResponseBody
	public Exception handleNullEmailIdException(NullEmailIdException e) {
		return new Exception(e.getErrorCode(), e.getErrorMsg());
	}
}
