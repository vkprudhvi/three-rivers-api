package com.threerivers.account.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.threerivers.account.constant.TransactionType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransactionPayload implements Serializable {
	private static final long serialVersionUID = 5301197743805206439L;
	private String accountNumber;
	private LocalDateTime transactionTs;
	private TransactionType type;
	private Double amount;

}
