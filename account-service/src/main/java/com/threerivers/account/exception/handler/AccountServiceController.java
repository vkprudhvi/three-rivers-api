package com.threerivers.account.exception.handler;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.threerivers.account.exception.AccountNotfoundException;
import com.threerivers.account.exception.UnkownTransactionTypeException;
import com.threerivers.account.model.ErrorResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
@RequestMapping(produces = "application/vnd.error+json")
public class AccountServiceController {

	@ExceptionHandler(value = AccountNotfoundException.class)
	public ResponseEntity<Object> exception(HttpServletRequest req, AccountNotfoundException e) {
		ErrorResponse er = new ErrorResponse();
		er.setTimestamp(new Date());
		er.setStatus(HttpStatus.NOT_FOUND.getReasonPhrase());
		er.setMessage(e.getMessage());
		er.setPath(req.getRequestURI());
		log.error(e.getMessage(), e);
		return new ResponseEntity<>(er, new HttpHeaders(), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(value = UnkownTransactionTypeException.class)
	public ResponseEntity<Object> exception(HttpServletRequest req, UnkownTransactionTypeException e) {
		ErrorResponse er = new ErrorResponse();
		er.setTimestamp(new Date());
		er.setStatus(HttpStatus.BAD_REQUEST.getReasonPhrase());
		er.setMessage(e.getMessage());
		er.setPath(req.getRequestURI());
		log.error(e.getMessage(), e);
		return new ResponseEntity<>(er, new HttpHeaders(), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> handleException(HttpServletRequest req, Exception e) {
		ErrorResponse er = new ErrorResponse();
		er.setTimestamp(new Date());
		er.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
		er.setMessage(getCause(e).getMessage());
		er.setPath(req.getRequestURI());
		log.error(e.getMessage(), e);
		return new ResponseEntity<>(er, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	public static Throwable getCause(Throwable th) {
		Throwable cause = th;
		while (cause != null && cause.getCause() != null) {
			cause = cause.getCause();
		}
		return cause;
	}
}
