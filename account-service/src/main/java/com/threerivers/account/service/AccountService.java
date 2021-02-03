package com.threerivers.account.service;

import com.threerivers.account.entity.Account;
import com.threerivers.account.model.AccountPayload;
import com.threerivers.account.model.AccountResponse;
import com.threerivers.account.model.TransactionPayload;

public interface AccountService {
	Account saveAccount(AccountPayload accountPayload);

	AccountResponse updateBalance(TransactionPayload transactionPayload);

	Account getAccount(String accountNumber);

	AccountResponse getBalance(String accountNumber);
}
