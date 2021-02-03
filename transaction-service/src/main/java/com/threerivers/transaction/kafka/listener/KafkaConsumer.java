package com.threerivers.transaction.kafka.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.threerivers.transaction.model.TransactionPayload;
import com.threerivers.transaction.service.TransactionService;

import lombok.extern.slf4j.Slf4j;

@Component
public class KafkaConsumer {
	@Autowired
	private TransactionService transactionService;

	@KafkaListener(topics = "feed_transaction", groupId = "group_transaction", containerFactory = "transactionKafkaListenerFactory")
	public void transactionListener(TransactionPayload transactionPayload) {
		transactionService.saveTransaction(transactionPayload);
	}

}
