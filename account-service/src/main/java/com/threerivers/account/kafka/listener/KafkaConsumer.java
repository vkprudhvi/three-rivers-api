package com.threerivers.account.kafka.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.threerivers.account.model.AccountPayload;
import com.threerivers.account.service.AccountService;

import lombok.extern.slf4j.Slf4j;

@Component
public class KafkaConsumer {
	@Autowired
	private AccountService accountService;

	@KafkaListener(topics = "feed_account", groupId = "group_account", containerFactory = "accountKafkaListenerFactory")
	public void accountListener(AccountPayload accountPayload) {
		accountService.saveAccount(accountPayload);
	}
}
