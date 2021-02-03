package com.threerivers.account.model;

import java.io.Serializable;
import java.time.LocalDateTime;

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
public class AccountPayload implements Serializable {
	private static final long serialVersionUID = -6581422242969715201L;
	private String accountNumber;
	private LocalDateTime lastUpdateTimestamp;
	private Double balance;
	
}
