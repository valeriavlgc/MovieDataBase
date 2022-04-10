package com.moviedatabase.controller.exceptions;

public class EntryAlreadyExists extends RuntimeException {
	
	public EntryAlreadyExists(String message) {
		super(message);
	}

}
