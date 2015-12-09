package com.humanize.server.controller;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.humanize.server.authentication.exception.PasswordResetFailedException;
import com.humanize.server.authentication.exception.TempPasswordSendingFailedException;
import com.humanize.server.authentication.exception.UserCreationFailedException;
import com.humanize.server.authentication.exception.UserDataNotFoundException;
import com.humanize.server.authentication.exception.UserInvitationFailedException;
import com.humanize.server.authentication.exception.UserLoginFailedException;
import com.humanize.server.authentication.exception.UserLogoutFailedException;
import com.humanize.server.authentication.exception.UserUpdationFailedException;
import com.humanize.server.authentication.exception.UserVerificationFailedException;
import com.humanize.server.authentication.exception.VerificationCodeSendingFailedException;
import com.humanize.server.data.User;
import com.humanize.server.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/users/signup")
	public ResponseEntity<User> signup(@RequestBody User user) throws UserCreationFailedException {
		return new ResponseEntity<User>(userService.signup(user), HttpStatus.OK);
	}
	
	@RequestMapping("/users/verify")
	public ResponseEntity<Boolean> verifyUser(@RequestParam("emailId") @NotEmpty @Email String emailId, @RequestParam("verificationCode") @NotEmpty String verificationCode) 
		throws UserVerificationFailedException {
		return new ResponseEntity<Boolean>(userService.verifyUser(emailId, verificationCode), HttpStatus.OK);
	}

	@RequestMapping("/users/login")
	public ResponseEntity<User> login(@RequestBody User user) throws UserLoginFailedException {
		return new ResponseEntity<User>(userService.login(user), HttpStatus.OK);
	}
	
	@RequestMapping("/users/sendTempPassword")
	public ResponseEntity<Boolean> sendTempPassword(@RequestParam("emailId") @NotEmpty @Email String emailId) throws TempPasswordSendingFailedException {
		return new ResponseEntity<Boolean>(userService.sendTempPassword(emailId), HttpStatus.OK);
	}
	
	@RequestMapping("/users/resetPassword")
	public ResponseEntity<User> resetPassword(@RequestBody User user) throws PasswordResetFailedException {
		return new ResponseEntity<User>(userService.resetPassword(user), HttpStatus.OK);
	}
	
	@RequestMapping("/users/sendVerificationCode")
	public ResponseEntity<Boolean> resendVerificationCode(@RequestParam("emailId") @NotEmpty @Email String emailId) throws VerificationCodeSendingFailedException {
		return new ResponseEntity<Boolean>(userService.sendVerificationCode(emailId), HttpStatus.OK);
	}
	
	@RequestMapping("/users/logout")
	public ResponseEntity<Boolean> logout(@RequestBody User user) throws UserLogoutFailedException {
		return new ResponseEntity<Boolean>(userService.logout(user), HttpStatus.OK);
	}
	
	@RequestMapping("/users/invite")
	public ResponseEntity<Boolean> inviteUser(@RequestParam("emailId") @NotEmpty @Email String emailId) throws UserInvitationFailedException {
		return new ResponseEntity<Boolean>(userService.inviteUser(emailId), HttpStatus.OK);
	}

	@RequestMapping("/users/data")
	public ResponseEntity<User> userdata(@RequestParam("emailId") @NotEmpty @Email String emailId) throws UserDataNotFoundException {
		return new ResponseEntity<User>(userService.getUserdata(emailId), HttpStatus.OK);
	}

	@RequestMapping("/users/update")
	public ResponseEntity<User> updateUser(@RequestBody User user) throws UserUpdationFailedException {
		return new ResponseEntity<User>(userService.updateUser(user), HttpStatus.OK);
	}
}
