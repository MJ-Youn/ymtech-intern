package com.dev2.intern.exception;

@SuppressWarnings("serial")
public class ExistEmailException extends Exception {

	public ExistEmailException() {
		super();
	}
	
	public ExistEmailException(String message) {
		super(message);
	}
	
}
