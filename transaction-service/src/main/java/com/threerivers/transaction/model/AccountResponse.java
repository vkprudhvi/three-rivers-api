package com.threerivers.transaction.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

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
public class AccountResponse implements Serializable {
 	private static final long serialVersionUID = 3382642181544449469L;
	@JsonProperty("account_number")
    private String accountNumber;
    private Double balance;
}
