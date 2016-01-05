package com.humanize.server.common;

import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class GlobalExceptionHandler {
	/*
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
	@ExceptionHandler(UserCreationFailedException.class)
	@ResponseBody
	public NetworkException handleUserCreationException(UserCreationFailedException e) {
		return new NetworkException(e.getErrorCode(), e.getErrorMsg());
	}
	
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(UserUpdationFailedException.class)
	@ResponseBody
	public NetworkException handleUserUpdationException(UserUpdationFailedException e) {
		return new NetworkException(e.getErrorCode(), e.getErrorMsg());
	}
	
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(UserNotFoundException.class)
	@ResponseBody
	public NetworkException handleUserNotFoundException(UserNotFoundException e) {
		return new NetworkException(e.getErrorCode(), e.getErrorMsg());
	}
	
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(UserDeletionFailedException.class)
	@ResponseBody
	public NetworkException handleUserDeletionException(UserDeletionFailedException exception) {
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
	} */
}
