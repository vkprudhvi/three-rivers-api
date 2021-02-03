package com.threerivers.account.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class AccountNotfoundException extends RuntimeException {
	private static final long serialVersionUID = 4394401239207463245L;

	public AccountNotfoundException(String message) {
		super(message);
	}

	public AccountNotfoundException(String message, Throwable th) {
		super(message, th);
	}
}
