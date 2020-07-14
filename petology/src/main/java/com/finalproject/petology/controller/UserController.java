package com.finalproject.petology.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.finalproject.petology.dao.UserProfileRepo;
import com.finalproject.petology.entity.User;
import com.finalproject.petology.entity.UserProfile;
import com.finalproject.petology.service.UserService;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {
	@Autowired
	private UserService userService;

	@Autowired
	private UserProfileRepo userProfileRepo;

	@GetMapping
	public Iterable<User> getUsers() {
		return userService.getUsers();
	}

	@GetMapping("/username")
	public Optional<User> getUserByUsername(@RequestParam String username) {
		return userService.getUserByUsername(username);
	}

	@GetMapping("/email")
	public Optional<User> getUserByEmail(@RequestParam String email) {
		return userService.getUserByEmail(email);
	}

	@GetMapping("/{userId}")
	public Optional<User> getUserById(@PathVariable int userId) {
		return userService.getUserById(userId);
	}

	@GetMapping("/login/{userId}")
	public User loginUser(@PathVariable int userId, @RequestParam String password) {
		User findUser = userService.loginUser(userId, password);
		if (findUser == null) {
			return null;
		}
		findUser.setPassword(null);
		return findUser;
	}

	@PostMapping("/register")
	public User registerUser(@RequestBody User user) {
		User savedUser = userService.registerUser(user);
		savedUser.setPassword(null);
		return savedUser;
	}

	@GetMapping("/verify/{username}")
	public User verifyUser(@PathVariable String username, @RequestParam String token) {
		return userService.verifyUser(username, token);
	}

	@GetMapping("/profile")
	public Iterable<UserProfile> getAllUserProfile() {
		return userProfileRepo.findAll();
	}

	@PutMapping("/profile")
	public User updateUserProfile(@RequestBody User user) {
		return userService.updateUserProfile(user);
	}

	@GetMapping("/{userId}/changePassword")
	public User changePassword(@PathVariable int userId, @RequestParam String oldPass, @RequestParam String newPass) {
		User findUser = userService.changePassword(userId, oldPass, newPass);
		if (findUser == null) {
			return null;
		}
		findUser.setPassword(null);
		return findUser;
	}

	@GetMapping("/forgot")
	public String forgotPassword(@RequestParam String email) {
		return userService.forgotPassword(email);
	}

	@GetMapping("/forgot/{username}")
	public String resetPassword(@PathVariable String username, @RequestParam String token,
			@RequestParam String password) {
		return userService.resetPassword(username, token, password);
	}
}
