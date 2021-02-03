package com.threerivers.transaction.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.threerivers.transaction.constant.TransactionType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TransactionPayload implements Serializable {
	private static final long serialVersionUID = 5301197743805206439L;
	private String accountNumber;
	private LocalDateTime transactionTs;
	private TransactionType type;
	private Double amount;

}
