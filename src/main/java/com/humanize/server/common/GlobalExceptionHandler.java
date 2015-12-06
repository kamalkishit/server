package com.humanize.server.common;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.humanize.server.authentication.exception.InvitationCodeCreationException;
import com.humanize.server.authentication.exception.InvitationCodeDeletionException;
import com.humanize.server.authentication.exception.InvitationCodeNotFoundException;
import com.humanize.server.authentication.exception.InvitationCodeUpdationException;
import com.humanize.server.authentication.exception.NullPasswordException;
import com.humanize.server.authentication.exception.NullTempPasswordException;
import com.humanize.server.authentication.exception.NullUserException;
import com.humanize.server.authentication.exception.NullVerificationCodeException;
import com.humanize.server.authentication.exception.UserCreationException;
import com.humanize.server.authentication.exception.UserDeletionException;
import com.humanize.server.authentication.exception.UserInvitationFailedException;
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
import com.humanize.server.content.exception.ContentCreationException;
import com.humanize.server.content.exception.ContentDeletionException;
import com.humanize.server.content.exception.ContentNotFoundException;
import com.humanize.server.content.exception.ContentUpdationException;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(UserInvitationFailedException.class)
	@ResponseBody
	public NetworkException handleUserInvitationFailedException(UserInvitationFailedException exception) {
		return new NetworkException(exception.getErrorCode(), exception.getErrorMsg());
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(WrongEmailIdException.class)
	@ResponseBody
	public NetworkException handleWrongEmailIdException(WrongEmailIdException e) {
		return new NetworkException(e.getErrorCode(), e.getErrorMsg());
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(NullPasswordException.class)
	@ResponseBody
	public NetworkException handleNullPasswordException(NullPasswordException e) {
		return new NetworkException(e.getErrorCode(), e.getErrorMsg());
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(WrongPasswordException.class)
	@ResponseBody
	public NetworkException handleWrongPasswordxception(WrongPasswordException e) {
		return new NetworkException(e.getErrorCode(), e.getErrorMsg());
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(NullTempPasswordException.class)
	@ResponseBody
	public NetworkException handleNullTempPasswordException(NullTempPasswordException e) {
		return new NetworkException(e.getErrorCode(), e.getErrorMsg());
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(WrongTempPasswordException.class)
	@ResponseBody
	public NetworkException handleWrongTempPasswordException(WrongTempPasswordException e) {
		return new NetworkException(e.getErrorCode(), e.getErrorMsg());
	}
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(WrongInvitationCodeException.class)
	@ResponseBody
	public NetworkException handleWrongInvitationCodeException(WrongInvitationCodeException e) {
		return new NetworkException(e.getErrorCode(), e.getErrorMsg());
	}
	
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(InvitationCodeCreationException.class)
	@ResponseBody
	public NetworkException handleInvitationCodeCreationException(InvitationCodeCreationException e) {
		return new NetworkException(e.getErrorCode(), e.getErrorMsg());
	}
	
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(InvitationCodeUpdationException.class)
	@ResponseBody
	public NetworkException handleInvitationCodeUpdationException(InvitationCodeUpdationException e) {
		return new NetworkException(e.getErrorCode(), e.getErrorMsg());
	}
	
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(InvitationCodeNotFoundException.class)
	@ResponseBody
	public NetworkException handleInvitationCodeNotFoundException(InvitationCodeNotFoundException e) {
		return new NetworkException(e.getErrorCode(), e.getErrorMsg());
	}
	
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(InvitationCodeDeletionException.class)
	@ResponseBody
	public NetworkException handleInvitationCodeDeletionException(InvitationCodeDeletionException e) {
		return new NetworkException(e.getErrorCode(), e.getErrorMsg());
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(NullVerificationCodeException.class)
	@ResponseBody
	public NetworkException handleNullVerificationCodeException(NullVerificationCodeException e) {
		return new NetworkException(e.getErrorCode(), e.getErrorMsg());
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(WrongVerificationCodeException.class)
	@ResponseBody
	public NetworkException handleWrongVerificationCodeException(WrongVerificationCodeException e) {
		return new NetworkException(e.getErrorCode(), e.getErrorMsg());
	}
	
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(VerificationCodeCreationException.class)
	@ResponseBody
	public NetworkException handleVerificationCodeCreationException(VerificationCodeCreationException e) {
		return new NetworkException(e.getErrorCode(), e.getErrorMsg());
	}
	
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(VerificationCodeUpdationException.class)
	@ResponseBody
	public NetworkException handleVerificationCodeUpdationException(VerificationCodeUpdationException e) {
		return new NetworkException(e.getErrorCode(), e.getErrorMsg());
	}
	
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(VerificationCodeNotFoundException.class)
	@ResponseBody
	public NetworkException handleVerificationCodeNotFoundException(VerificationCodeNotFoundException e) {
		return new NetworkException(e.getErrorCode(), e.getErrorMsg());
	}
	
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(VerificationCodeDeletionException.class)
	@ResponseBody
	public NetworkException handleVerificationCodeDeletionException(VerificationCodeDeletionException e) {
		return new NetworkException(e.getErrorCode(), e.getErrorMsg());
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(NullUserException.class)
	@ResponseBody
	public NetworkException handleNullUserException(NullUserException e) {
		return new NetworkException(e.getErrorCode(), e.getErrorMsg());
	}
	
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(UserCreationException.class)
	@ResponseBody
	public NetworkException handleUserCreationException(UserCreationException e) {
		return new NetworkException(e.getErrorCode(), e.getErrorMsg());
	}
	
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(UserUpdationException.class)
	@ResponseBody
	public NetworkException handleUserUpdationException(UserUpdationException e) {
		return new NetworkException(e.getErrorCode(), e.getErrorMsg());
	}
	
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(UserNotFoundException.class)
	@ResponseBody
	public NetworkException handleUserNotFoundException(UserNotFoundException e) {
		return new NetworkException(e.getErrorCode(), e.getErrorMsg());
	}
	
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(UserDeletionException.class)
	@ResponseBody
	public NetworkException handleUserDeletionException(UserDeletionException exception) {
		return new NetworkException(exception.getErrorCode(), exception.getErrorMsg());
	}
	
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(ContentCreationException.class)
	@ResponseBody
	public NetworkException handleContentCreationException(ContentCreationException exception) {
		exception.printStackTrace();
		return new NetworkException(exception.getErrorCode(), exception.getErrorMsg());
	}
	
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(ContentUpdationException.class)
	@ResponseBody
	public NetworkException handleContentUpdationException(ContentUpdationException exception) {
		return new NetworkException(exception.getErrorCode(), exception.getErrorMsg());
	}
	
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(ContentNotFoundException.class)
	@ResponseBody
	public NetworkException handleContentNotFoundException(ContentNotFoundException exception) {
		return new NetworkException(exception.getErrorCode(), exception.getErrorMsg());
	}
	
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(ContentDeletionException.class)
	@ResponseBody
	public NetworkException handleContentDeletionException(ContentDeletionException exception) {
		return new NetworkException(exception.getErrorCode(), exception.getErrorMsg());
	}
}
