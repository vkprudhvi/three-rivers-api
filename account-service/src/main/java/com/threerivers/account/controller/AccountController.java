package com.threerivers.account.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.threerivers.account.model.AccountResponse;
import com.threerivers.account.model.TransactionPayload;
import com.threerivers.account.service.AccountService;

@RestController
@RequestMapping(value = "/api/v1/account")
public class AccountController {
	@Autowired
	private AccountService accountService;

	@GetMapping("/check-balance/{accountNumber}")
	public ResponseEntity<AccountResponse> getBalance(@PathVariable String accountNumber) {
		return ResponseEntity.ok(accountService.getBalance(accountNumber));
	}

	@PostMapping("/update-balance")
	public ResponseEntity<AccountResponse> updateBalance(@RequestBody TransactionPayload transactionPayload) {
		return ResponseEntity.ok(accountService.updateBalance(transactionPayload));
	}
}
