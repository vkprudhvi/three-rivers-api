package com.threerivers.transaction.service.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.threerivers.transaction.client.AccountFeignClient;
import com.threerivers.transaction.constant.StatementDurationType;
import com.threerivers.transaction.entity.Transaction;
import com.threerivers.transaction.model.AccountResponse;
import com.threerivers.transaction.model.TransactionPayload;
import com.threerivers.transaction.repository.TransactionRepository;
import com.threerivers.transaction.service.MongoSequenceService;
import com.threerivers.transaction.service.TransactionService;

import lombok.extern.slf4j.Slf4j;

@Service
public class TransactionServiceImpl implements TransactionService {
	@Autowired
	private AccountFeignClient accountFeignClient;
	@Autowired
	private MongoSequenceService mongoSequenceService;
	@Autowired
	private TransactionRepository transactionRepository;

	@Transactional
	@Override
	public void saveTransaction(TransactionPayload transactionPayload) {
		Transaction transaction = new Transaction();
		long id = mongoSequenceService.getSequenceNumber(Transaction.SEQUENCE_NAME);
		transaction.setId(id);
		BeanUtils.copyProperties(transactionPayload, transaction);
		ResponseEntity<AccountResponse> re = accountFeignClient.updateBalance(transactionPayload);
		AccountResponse accountResponse = re.getBody();
		transactionRepository.save(transaction);
	}

	@Override
	public Page<Transaction> getTransactions(String accountNumber, LocalDate from, LocalDate to,
			List<String> transactionType, Pageable pageable) {
		LocalDateTime fromLocalDateTime = from.atStartOfDay();
		LocalDateTime toLocalDateTime = to.atTime(23, 59, 59, 999999999);
		System.out.println("From: " + fromLocalDateTime + " To: " + toLocalDateTime);
		return transactionRepository.findTransactions(accountNumber, fromLocalDateTime, toLocalDateTime,
				transactionType, pageable);
	}

	@Override
	public Page<Transaction> getTransactions(String accountNumber, long statementDuration,
			StatementDurationType statementDurationType, List<String> transactionType, Pageable pageable) {
		LocalDate toDay = LocalDate.now();
		LocalDate from;
		// If user passes statementDuration 0 or 1 we get current day/month/year
		// statement
		if (statementDuration < 2) {
			// 0 current day/month/year
			statementDuration = 0;
		} else {
			statementDuration = statementDuration - 1;
		}
		switch (statementDurationType) {
		case DAYS:
			from = toDay.minusDays(statementDuration);
			break;
		case MONTHS:
			LocalDate lastNthMonth = toDay.minusMonths(statementDuration);
			from = LocalDate.of(lastNthMonth.getYear(), lastNthMonth.getMonth(), 01);
			break;
		case YEARS:
			LocalDate lastNthYear = toDay.minusYears(statementDuration);
			from = LocalDate.of(lastNthYear.getYear(), 01, 01);
			break;
		default:
			throw new RuntimeException("Invalid StatementDuration");
		}
		System.out.println("Getting statement from  " + from + " to " + toDay);
		return transactionRepository.findTransactions(accountNumber, from, transactionType, pageable);
	}
}
