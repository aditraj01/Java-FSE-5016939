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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/auth")
@Tag(name = "Auth", description = "Auth Api")
@SecurityRequirement(name = "bearerAuth")
public class AuthController {

	@Autowired
	private UserDataService service;

	@Operation(summary = "User login" , description = "Login user to access the database")
	@ApiResponse(responseCode = "200", description = "User logged in successfully")
	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody UserData user) {
		String token = service.verify(user);
		return new ResponseEntity<>(token, HttpStatus.OK);
	}
	
	@Operation(summary = "Create a new User", description = "Create a new User in the Database")
    @ApiResponse(responseCode = "201", description = "User created successfully")
	@PostMapping("/register")
	public ResponseEntity<UserData> register(@RequestBody UserData user){
		UserData createdUser = service.createUser(user);
		return new ResponseEntity<UserData>(createdUser, HttpStatus.CREATED);
	}

}
