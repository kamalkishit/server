
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

import com.humanize.server.authentication.exception.ForgotPasswordException;
import com.humanize.server.authentication.exception.ResetPasswordException;
import com.humanize.server.authentication.exception.UserCreationException;
import com.humanize.server.authentication.exception.UserInvitationException;
import com.humanize.server.authentication.exception.UserNotFoundException;
import com.humanize.server.authentication.exception.UserUpdationException;
import com.humanize.server.data.LoginUser;
import com.humanize.server.data.ResetPasswordUser;
import com.humanize.server.data.SignupUser;
import com.humanize.server.data.User;
import com.humanize.server.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/users/signup")
	public ResponseEntity<User> signup(@RequestBody SignupUser signupUser) throws UserCreationException {
		return new ResponseEntity<User>(userService.signup(signupUser), HttpStatus.OK);
	}

	@RequestMapping("/users/login")
	public ResponseEntity<User> login(@RequestBody LoginUser loginUser) throws UserNotFoundException {
		return new ResponseEntity<User>(userService.login(loginUser), HttpStatus.OK);
	}
	
	@RequestMapping("/users/data")
	public ResponseEntity<User> userdata(@RequestParam("emailId") @NotEmpty @Email String emailId) throws UserNotFoundException {
		return new ResponseEntity<User>(userService.getUserdata(emailId), HttpStatus.OK);
	}

	@RequestMapping("/users/update")
	public ResponseEntity<User> updateUser(@RequestBody User user) throws UserUpdationException {
		return new ResponseEntity<User>(userService.updateUser(user), HttpStatus.OK);
	}
	
	@RequestMapping("/users/forgot")
	public ResponseEntity<Boolean> forgotPassword(@RequestParam("emailId") @NotEmpty @Email String emailId) throws ForgotPasswordException {
		return new ResponseEntity<Boolean>(userService.forgotPassword(emailId), HttpStatus.OK);
	}
	
	@RequestMapping("/users/reset")
	public ResponseEntity<User> resetPassword(@RequestBody ResetPasswordUser resetPasswordUser) throws ResetPasswordException {
		return new ResponseEntity<User>(userService.resetPassword(resetPasswordUser), HttpStatus.OK);
	}
	
	@RequestMapping("/users/invite")
	public ResponseEntity<Boolean> inviteUser(@RequestParam("emailId") @NotEmpty @Email String emailId) throws UserInvitationException {
		return new ResponseEntity<Boolean>(userService.inviteUser(emailId), HttpStatus.OK);
	}

	@RequestMapping("/users/recommend")
	public ResponseEntity<Boolean> recommend(@RequestParam("userId") @NotEmpty String userId, @RequestParam("contentId") @NotEmpty String contentId, @RequestParam("flag") boolean flag) throws UserUpdationException {
		return new ResponseEntity<Boolean>(userService.recommend(userId, contentId, flag), HttpStatus.OK);
	}
	
	@RequestMapping("/users/bookmark")
	public ResponseEntity<Boolean> bookmark(@RequestParam("userId") @NotEmpty String userId, @RequestParam("contentId") @NotEmpty String contentId, @RequestParam("flag") boolean flag) throws UserUpdationException {
		return new ResponseEntity<Boolean>(userService.bookmark(userId, contentId, flag), HttpStatus.OK);
	}
}
