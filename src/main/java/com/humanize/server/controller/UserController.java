
package com.humanize.server.controller;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.humanize.server.authentication.exception.ForgotPasswordException;
import com.humanize.server.authentication.exception.ResetPasswordException;
import com.humanize.server.authentication.exception.UserCreationException;
import com.humanize.server.authentication.exception.UserInvitationException;
import com.humanize.server.authentication.exception.UserNotFoundException;
import com.humanize.server.authentication.exception.UserUpdateException;
import com.humanize.server.common.JsonWebTokenServiceImpl;
import com.humanize.server.data.LoginUser;
import com.humanize.server.data.ResetPasswordUser;
import com.humanize.server.data.SignupUser;
import com.humanize.server.data.User;
import com.humanize.server.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/user/signup")
	public ResponseEntity<User> signup(@RequestBody SignupUser signupUser) throws UserCreationException {
		return new ResponseEntity<User>(userService.signup(signupUser), HttpStatus.OK);
	}

	@RequestMapping("/user/login")
	public ResponseEntity<String> login(@RequestBody LoginUser loginUser) throws UserNotFoundException {
		return new ResponseEntity<String>(userService.login(loginUser), HttpStatus.OK);
	}
	
	@RequestMapping("/user/data")
	public ResponseEntity<User> userdata(@RequestHeader(value="token") String token) throws UserNotFoundException {
		return new ResponseEntity<User>(userService.getUserdata(token), HttpStatus.OK);
	}

	@RequestMapping("/user/update")
	public ResponseEntity<User> updateUser(@RequestHeader(value="token") String token, @RequestBody User user) throws UserUpdateException {
		return new ResponseEntity<User>(userService.updateUser(token, user), HttpStatus.OK);
	}
	
	@RequestMapping("/user/forgot")
	public ResponseEntity<Boolean> forgotPassword(@RequestParam("emailId") @NotEmpty @Email String emailId) throws ForgotPasswordException {
		return new ResponseEntity<Boolean>(userService.forgotPassword(emailId), HttpStatus.OK);
	}
	
	@RequestMapping("/user/reset")
	public ResponseEntity<User> resetPassword(@RequestBody ResetPasswordUser resetPasswordUser) throws ResetPasswordException {
		return new ResponseEntity<User>(userService.resetPassword(resetPasswordUser), HttpStatus.OK);
	}
	
	@RequestMapping("/user/invite")
	public ResponseEntity<Boolean> inviteUser(@RequestHeader(value="token") String token, @RequestParam("emailId") @NotEmpty @Email String emailId) throws UserInvitationException {
		return new ResponseEntity<Boolean>(userService.inviteUser(token, emailId), HttpStatus.OK);
	}

	@RequestMapping("/user/recommend")
	public ResponseEntity<Boolean> recommend(@RequestHeader(value="token") String token, @RequestParam("contentId") @NotEmpty String contentId, @RequestParam("flag") boolean flag) throws UserUpdateException {
		return new ResponseEntity<Boolean>(userService.recommend(token, contentId, flag), HttpStatus.OK);
	}
	
	@RequestMapping("/user/bookmark")
	public ResponseEntity<Boolean> bookmark(@RequestHeader(value="token") String token, @RequestParam("contentId") @NotEmpty String contentId, @RequestParam("flag") boolean flag) throws UserUpdateException {
		return new ResponseEntity<Boolean>(userService.bookmark(token, contentId, flag), HttpStatus.OK);
	}
}
