package com.humanize.server.common;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.humanize.server.authentication.exception.InvalidEmailIdException;
import com.humanize.server.authentication.exception.InvalidPasswordException;
import com.humanize.server.authentication.exception.InvitationCodeCreationException;
import com.humanize.server.authentication.exception.InvitationCodeDeletionException;
import com.humanize.server.authentication.exception.InvitationCodeNotFoundException;
import com.humanize.server.authentication.exception.InvitationCodeUpdationException;
import com.humanize.server.authentication.exception.NullEmailIdException;
import com.humanize.server.authentication.exception.NullInvitationCodeException;
import com.humanize.server.authentication.exception.NullPasswordException;
import com.humanize.server.authentication.exception.NullTempPasswordException;
import com.humanize.server.authentication.exception.NullUserException;
import com.humanize.server.authentication.exception.NullVerificationCodeException;
import com.humanize.server.authentication.exception.UserCreationException;
import com.humanize.server.authentication.exception.UserDeletionException;
import com.humanize.server.authentication.exception.UserNotFoundException;
import com.humanize.server.authentication.exception.UserUpdationException;
import com.humanize.server.authentication.exception.VerificationCodeCreationException;
import com.humanize.server.authentication.exception.VerificationCodeDeletionException;
import com.humanize.server.authentication.exception.VerificationCodeNotFoundException;
import com.humanize.server.authentication.exception.VerificationCodeUpdationException;
import com.humanize.server.authentication.exception.WrongEmailIdException;
import com.humanize.server.authentication.exception.WrongInvitationCodeException;
import com.humanize.server.authentication.exception.WrongPasswordException;
import com.humanize.server.authentication.exception.WrongTempPasswordException;
import com.humanize.server.authentication.exception.WrongVerificationCodeException;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	// Authentication
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(NullEmailIdException.class)
	@ResponseBody
	public Exception handleNullEmailIdException(NullEmailIdException e) {
		return new Exception(e.getErrorCode(), e.getErrorMsg());
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(InvalidEmailIdException.class)
	@ResponseBody
	public Exception handleInvalidEmailIdException(InvalidEmailIdException e) {
		return new Exception(e.getErrorCode(), e.getErrorMsg());
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(WrongEmailIdException.class)
	@ResponseBody
	public Exception handleWrongEmailIdException(WrongEmailIdException e) {
		return new Exception(e.getErrorCode(), e.getErrorMsg());
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(NullPasswordException.class)
	@ResponseBody
	public Exception handleNullPasswordException(NullPasswordException e) {
		return new Exception(e.getErrorCode(), e.getErrorMsg());
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(InvalidPasswordException.class)
	@ResponseBody
	public Exception handleInvalidPasswordException(InvalidPasswordException e) {
		return new Exception(e.getErrorCode(), e.getErrorMsg());
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(WrongPasswordException.class)
	@ResponseBody
	public Exception handleWrongPasswordxception(WrongPasswordException e) {
		return new Exception(e.getErrorCode(), e.getErrorMsg());
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(NullTempPasswordException.class)
	@ResponseBody
	public Exception handleNullTempPasswordException(NullTempPasswordException e) {
		return new Exception(e.getErrorCode(), e.getErrorMsg());
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(WrongTempPasswordException.class)
	@ResponseBody
	public Exception handleWrongTempPasswordException(WrongTempPasswordException e) {
		return new Exception(e.getErrorCode(), e.getErrorMsg());
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(NullInvitationCodeException.class)
	@ResponseBody
	public Exception handleNullInvitationCodeException(NullInvitationCodeException e) {
		return new Exception(e.getErrorCode(), e.getErrorMsg());
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(WrongInvitationCodeException.class)
	@ResponseBody
	public Exception handleWrongInvitationCodeException(WrongInvitationCodeException e) {
		return new Exception(e.getErrorCode(), e.getErrorMsg());
	}
	
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(InvitationCodeCreationException.class)
	@ResponseBody
	public Exception handleInvitationCodeCreationException(InvitationCodeCreationException e) {
		return new Exception(e.getErrorCode(), e.getErrorMsg());
	}
	
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(InvitationCodeUpdationException.class)
	@ResponseBody
	public Exception handleInvitationCodeUpdationException(InvitationCodeUpdationException e) {
		return new Exception(e.getErrorCode(), e.getErrorMsg());
	}
	
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(InvitationCodeNotFoundException.class)
	@ResponseBody
	public Exception handleInvitationCodeNotFoundException(InvitationCodeNotFoundException e) {
		return new Exception(e.getErrorCode(), e.getErrorMsg());
	}
	
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(InvitationCodeDeletionException.class)
	@ResponseBody
	public Exception handleInvitationCodeDeletionException(InvitationCodeDeletionException e) {
		return new Exception(e.getErrorCode(), e.getErrorMsg());
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(NullVerificationCodeException.class)
	@ResponseBody
	public Exception handleNullVerificationCodeException(NullVerificationCodeException e) {
		return new Exception(e.getErrorCode(), e.getErrorMsg());
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(WrongVerificationCodeException.class)
	@ResponseBody
	public Exception handleWrongVerificationCodeException(WrongVerificationCodeException e) {
		return new Exception(e.getErrorCode(), e.getErrorMsg());
	}
	
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(VerificationCodeCreationException.class)
	@ResponseBody
	public Exception handleVerificationCodeCreationException(VerificationCodeCreationException e) {
		return new Exception(e.getErrorCode(), e.getErrorMsg());
	}
	
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(VerificationCodeUpdationException.class)
	@ResponseBody
	public Exception handleVerificationCodeUpdationException(VerificationCodeUpdationException e) {
		return new Exception(e.getErrorCode(), e.getErrorMsg());
	}
	
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(VerificationCodeNotFoundException.class)
	@ResponseBody
	public Exception handleVerificationCodeNotFoundException(VerificationCodeNotFoundException e) {
		return new Exception(e.getErrorCode(), e.getErrorMsg());
	}
	
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(VerificationCodeDeletionException.class)
	@ResponseBody
	public Exception handleVerificationCodeDeletionException(VerificationCodeDeletionException e) {
		return new Exception(e.getErrorCode(), e.getErrorMsg());
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(NullUserException.class)
	@ResponseBody
	public Exception handleNullUserException(NullUserException e) {
		return new Exception(e.getErrorCode(), e.getErrorMsg());
	}
	
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(UserCreationException.class)
	@ResponseBody
	public Exception handleUserCreationException(UserCreationException e) {
		return new Exception(e.getErrorCode(), e.getErrorMsg());
	}
	
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(UserUpdationException.class)
	@ResponseBody
	public Exception handleUserUpdationException(UserUpdationException e) {
		return new Exception(e.getErrorCode(), e.getErrorMsg());
	}
	
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(UserNotFoundException.class)
	@ResponseBody
	public Exception handleUserNotFoundException(UserNotFoundException e) {
		return new Exception(e.getErrorCode(), e.getErrorMsg());
	}
	
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(UserDeletionException.class)
	@ResponseBody
	public Exception handleUserDeletionException(UserDeletionException e) {
		return new Exception(e.getErrorCode(), e.getErrorMsg());
	}
}
