package com.mindtree.exception;

public class InvalidTokenException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	public InvalidTokenException() {
		super();
	}
	public InvalidTokenException(String msg) {
		super(msg);
	}

}
