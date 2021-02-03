package com.threerivers.transaction.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.threerivers.transaction.constant.StatementDurationType;
import com.threerivers.transaction.entity.Transaction;
import com.threerivers.transaction.model.TransactionPayload;

public interface TransactionService {
	void saveTransaction(TransactionPayload transactionPayload);

	Page<Transaction> getTransactions(String accountNumber, LocalDate from, LocalDate to, List<String> transactionType, Pageable pageable);

	Page<Transaction> getTransactions(String accountNumber, long statementDuration,
			StatementDurationType statementDurationType, List<String> transactionType, Pageable pageable);
}
