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
	public ResponseEntity<?> login(@RequestBody User user) {
		System.out.println("login");
		User userTemp = userService.login(user);

		if (userTemp != null) {
			return new ResponseEntity<User>(userTemp, HttpStatus.OK);
		}

		return new ResponseEntity<User>(userTemp,
				HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@RequestMapping("/users/data")
	public ResponseEntity<?> userdata() {
		User user = userService.getUserdata("pandey.kishore@gmail.com");

		if (user != null) {
			return new ResponseEntity<User>(user, HttpStatus.OK);
		}

		return new ResponseEntity<User>(user, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@RequestMapping("/users/signup")
	public ResponseEntity<?> signup(@RequestBody User user) {
		user = userService.createUser(user);

		if (user != null) {
			return new ResponseEntity<User>(user, HttpStatus.OK);
		}

		return new ResponseEntity<User>(user, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@RequestMapping("/users/update")
	public ResponseEntity<?> updateUser(@RequestBody User user) {
		user = userService.updateUser(user);

		if (user != null) {
			return new ResponseEntity<User>(user, HttpStatus.OK);
		}

		return new ResponseEntity<User>(user, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@RequestMapping("/likecontent")
	public ResponseEntity<?> likeContent(@RequestParam("userId") String userId,
			@RequestParam("contentId") String contentId) {
		if (userService.likeContent(userId, contentId)) {
			return new ResponseEntity<Boolean>(true, HttpStatus.OK);
		}

		return new ResponseEntity<Boolean>(false,
				HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@RequestMapping("/unlikecontent")
	public ResponseEntity<?> unlikeContent(
			@RequestParam("userId") String userId,
			@RequestParam("contentId") String contentId) {
		if (userService.unlikeContent(userId, contentId)) {
			return new ResponseEntity<Boolean>(true, HttpStatus.OK);
		}

		return new ResponseEntity<Boolean>(false,
				HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@RequestMapping("/bookmarkcontent")
	public ResponseEntity<?> bookmarkContent(
			@RequestParam("userId") String userId,
			@RequestParam("contentId") String contentId) {
		if (userService.bookmarkContent(userId, contentId)) {
			return new ResponseEntity<Boolean>(true, HttpStatus.OK);
		}

		return new ResponseEntity<Boolean>(false,
				HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@RequestMapping("/unbookmarkcontent")
	public ResponseEntity<?> unbookmarkContent(
			@RequestParam("userId") String userId,
			@RequestParam("contentId") String contentId) {
		if (userService.unbookmarkContent(userId, contentId)) {
			return new ResponseEntity<Boolean>(true, HttpStatus.OK);
		}

		return new ResponseEntity<Boolean>(false,
				HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
