package com.bookstore.exception;

public class UserAlreadyExistsError extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public UserAlreadyExistsError(String message) {
		super(message);
	}

}
