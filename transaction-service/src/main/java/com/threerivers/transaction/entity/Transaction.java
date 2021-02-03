package com.threerivers.transaction.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.threerivers.transaction.constant.TransactionType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "transactions")
public class Transaction {
	@Transient
	public static final String SEQUENCE_NAME = "transactions_id_seq";
	@Id
	private long id;
	private String accountNumber;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss.SSS")
	private LocalDateTime transactionTs;
	private TransactionType type;
	private Double amount;

}
