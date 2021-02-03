package com.threerivers.account.model;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse implements Serializable {
	private static final long serialVersionUID = 644859414365968792L;
	private Date timestamp;
	private String status;
	private String message;
	private String path;

}
