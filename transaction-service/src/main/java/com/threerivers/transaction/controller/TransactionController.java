package com.threerivers.transaction.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.threerivers.transaction.constant.StatementDurationType;
import com.threerivers.transaction.constant.TransactionType;
import com.threerivers.transaction.entity.Transaction;
import com.threerivers.transaction.model.TransactionPayload;
import com.threerivers.transaction.service.TransactionService;

@RestController
@RequestMapping(value = "/api/v1/transaction")
public class TransactionController {
	@Autowired
	private TransactionService transactionService;

	@PostMapping
	public ResponseEntity<Void> saveTransaction(@RequestBody TransactionPayload transactionRequest) {
		transactionService.saveTransaction(transactionRequest);
		return ResponseEntity.ok().build();
	}

	@GetMapping("/statement/{accountNumber}/{statementDurationType}/{statementDuration}")
	public ResponseEntity<Page<Transaction>> getTransactions(
			@PathVariable String accountNumber,
			@PathVariable StatementDurationType statementDurationType, 
			@PathVariable long statementDuration,
			@RequestParam(name = "transactionType", required = false) String transactionType,
			@PageableDefault(page = 0, size = 5) @SortDefault.SortDefaults(@SortDefault(sort = "transactionTs", direction = Sort.Direction.DESC)) Pageable pageable) {
		List<String> types = new ArrayList<>();
		if (transactionType == null || transactionType.isEmpty()) {
			TransactionType[] transactionTypes = TransactionType.values();
			for (TransactionType tt : transactionTypes) {
				types.add(tt.toString());
			}
		} else {
			types.add(TransactionType.valueOf(transactionType).toString());
		}
		return ResponseEntity.ok(transactionService.getTransactions(accountNumber, statementDuration,
				statementDurationType, types, pageable));
	}

	@GetMapping("/statement-range")
	public ResponseEntity<Page<Transaction>> getTransations(
			@RequestParam("accountNumber") String accountNumber,
			@RequestParam("from") String from, 
			@RequestParam("to") String to,
			@RequestParam(name = "transactionType", required = false) String transactionType,
			@PageableDefault(page = 0, size = 5) @SortDefault.SortDefaults(@SortDefault(sort = "transactionTs", direction = Sort.Direction.DESC)) Pageable pageable) {
	// @formatter:on
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
		LocalDate fromLocalDate = LocalDate.parse(from, formatter);
		LocalDate toLocalDate = LocalDate.parse(to, formatter);
		List<String> types = new ArrayList<>();
		if (transactionType == null || transactionType.isEmpty()) {
			TransactionType[] transactionTypes = TransactionType.values();
			for (TransactionType tt : transactionTypes) {
				types.add(tt.toString());
			}
		} else {
			types.add(TransactionType.valueOf(transactionType).toString());
		}
		return ResponseEntity
				.ok(transactionService.getTransactions(accountNumber, fromLocalDate, toLocalDate, types, pageable));
	}

}
