package com.bookstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.bookstore.entity.UserData;
import com.bookstore.service.UserDataService;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private UserDataService service;

	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody UserData user) {
		String token = service.verify(user);
		return new ResponseEntity<>(token, HttpStatus.OK);
	}
	
	@PostMapping("/register")
	public ResponseEntity<UserData> register(@RequestBody UserData user){
		UserData createdUser = service.createUser(user);
		return new ResponseEntity<UserData>(createdUser, HttpStatus.CREATED);
	}

}
