package com.threerivers.transaction.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.PostMapping;

import com.threerivers.transaction.model.AccountResponse;
import com.threerivers.transaction.model.TransactionPayload;

@FeignClient(name = "account-feign-client", url= "http://account-service")
public interface AccountFeignClient {
	
	@PostMapping(value = "/api/v1/account/update-balance", consumes = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<AccountResponse> updateBalance(TransactionPayload transactionPayload);
}
