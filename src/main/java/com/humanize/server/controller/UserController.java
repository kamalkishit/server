package com.humanize.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.humanize.server.data.User;
import com.humanize.server.service.UserService;

@RestController
public class UserController {
	@Autowired
	private UserService userService;

	@RequestMapping("/users/login")
	public ResponseEntity<User> login(@RequestBody User user) {
		return new ResponseEntity<User>(userService.login(user), HttpStatus.OK);
	}

	@RequestMapping("/users/data")
	public ResponseEntity<?> userdata(@RequestParam("emailId") String emailId) {
		return new ResponseEntity<User>(userService.getUserdata(emailId), HttpStatus.OK);
	}

	@RequestMapping("/users/signup")
	public ResponseEntity<User> signup(@RequestBody User user) {
		return new ResponseEntity<User>(userService.signup(user), HttpStatus.OK);
	}

	@RequestMapping("/users/update")
	public ResponseEntity<User> updateUser(@RequestBody User user) {
		user = userService.updateUser(user);

		if (user != null) {
			return new ResponseEntity<User>(user, HttpStatus.OK);
		}

		return new ResponseEntity<User>(user, HttpStatus.INTERNAL_SERVER_ERROR);
	}



}
