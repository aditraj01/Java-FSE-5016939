package com.bookstore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.bookstore.entity.UserData;
import com.bookstore.repository.UserRepository;
import com.bookstore.security.JwtHelper;

@Service
public class UserDataService {
	
	@Autowired
	private UserRepository repo;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private JwtHelper helper;
	@Autowired
	private MyUserDetailService userDetailsService;
	
	private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(15);
	
	public UserData createUser(UserData user) {
		user.setPassword(encoder.encode(user.getPassword()));
		return repo.save(user);
	}
	
	public String verify(UserData user) {
		
		doAuthenticate(user.getUsername(), user.getPassword());

		UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUsername());
		String token = helper.generateToken(userDetails);
		
		return token;
	}
	
	private void doAuthenticate(String username, String password) {
		try {
			Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
			System.out.println("Is Authenticated : " + authentication.isAuthenticated());
		} catch (BadCredentialsException e) {
			throw new BadCredentialsException(" Invalid Username or Password  !!");
		}

	}

}
