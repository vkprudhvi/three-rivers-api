package com.threerivers.transaction.repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.threerivers.transaction.entity.Transaction;

public interface TransactionRepository extends MongoRepository<Transaction, Long> {

	@Query("{$and : [{'accountNumber': ?0}, {'transactionTs': {$gte: ?1, $lte: ?2}}, {'type': {$in: ?3}}]}")
	Page<Transaction> findTransactions(String accountNumber, LocalDateTime from, LocalDateTime to,
			List<String> transactionType, Pageable pageable);

	@Query("{$and : [{'accountNumber': ?0}, {'transactionTs': {$gte: ?1}}, {'type': {$in: ?2}}]}")
	Page<Transaction> findTransactions(String accountNumber, LocalDate from, List<String> transactionType,
			Pageable pageable);
}