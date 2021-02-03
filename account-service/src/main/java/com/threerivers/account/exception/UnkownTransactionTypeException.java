package com.threerivers.account.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class UnkownTransactionTypeException extends RuntimeException {
	private static final long serialVersionUID = 2731270849567341813L;

	public UnkownTransactionTypeException(String message) {
		super(message);
	}

	public UnkownTransactionTypeException(String message, Throwable th) {
		super(message, th);
	}
}
