package com.humanize.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.humanize.server.data.ContactUs;
import com.humanize.server.data.InviteUser;
import com.humanize.server.data.LoginUser;
import com.humanize.server.data.ResetPasswordUser;
import com.humanize.server.data.SignupUser;
import com.humanize.server.data.SuggestArticle;
import com.humanize.server.data.User;
import com.humanize.server.exception.EmailSendingException;
import com.humanize.server.exception.UserCreationException;
import com.humanize.server.exception.UserNotFoundException;
import com.humanize.server.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	UserService userService;

	@RequestMapping("/api/user/invite")
	public ResponseEntity<Boolean> invite(@RequestBody InviteUser inviteUser) throws EmailSendingException {
		return new ResponseEntity<Boolean>(userService.invite(inviteUser), HttpStatus.OK);
	}
	
	@RequestMapping("/api/user/suggest")
	public ResponseEntity<Boolean> suggest(@RequestBody SuggestArticle suggestArticle) throws EmailSendingException {
		return new ResponseEntity<Boolean>(userService.suggest(suggestArticle), HttpStatus.OK);
	}
	
	@RequestMapping("/api/user/submit")
	public ResponseEntity<Boolean> submit(@RequestBody SuggestArticle suggestArticle) throws EmailSendingException {
		return new ResponseEntity<Boolean>(userService.suggest(suggestArticle), HttpStatus.OK);
	}
	
	@RequestMapping("/api/user/contactUs")
	public ResponseEntity<Boolean> contactUs(@RequestBody ContactUs contactUs) throws EmailSendingException {
		return new ResponseEntity<Boolean>(userService.contactUs(contactUs), HttpStatus.OK);
	}
	
	@RequestMapping("/api/user/signup")
	public ResponseEntity<User> signup(@RequestBody SignupUser signupUser) throws UserCreationException {
		return new ResponseEntity<User>(userService.signup(signupUser), HttpStatus.OK);
	}
	
	@RequestMapping("/api/user/login")
	public ResponseEntity<String> login(@RequestBody LoginUser loginUser) throws UserNotFoundException {
		return new ResponseEntity<String>(userService.login(loginUser), HttpStatus.OK);
	}

	@RequestMapping("/api/user/userData")
	public ResponseEntity<User> userData(@RequestParam("token") String token) throws UserNotFoundException {
		return new ResponseEntity<User>(userService.findByToken(token), HttpStatus.OK);
	}
	
	@RequestMapping("/api/user/forgot")
	public ResponseEntity<Boolean> forgotPassword(@RequestParam("emailId") String emailId) throws EmailSendingException {
		return new ResponseEntity<Boolean>(userService.forgotPassword(emailId), HttpStatus.OK);
	}
	
	@RequestMapping("/api/user/reset")
	public ResponseEntity<Boolean> resetPassword(@RequestBody ResetPasswordUser resetPasswordUser) throws Exception {
		return new ResponseEntity<Boolean>(userService.resetPassword(resetPasswordUser), HttpStatus.OK);
	}
}
