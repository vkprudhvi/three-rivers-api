package com.threerivers.account.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.NaturalId;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "account", uniqueConstraints = @UniqueConstraint(columnNames = "account_number", name = "uk_account_number"))
@DynamicInsert
@DynamicUpdate
public class Account {
	@Id
	@SequenceGenerator(name = "ACCOUNT_ID_GEN", sequenceName = "account_id_seq", initialValue = 1, allocationSize = 1)
	@GeneratedValue(generator = "ACCOUNT_ID_GEN", strategy = GenerationType.SEQUENCE)
	private Long id;
	@NaturalId
	@Column(name = "account_number", length = 25)
	private String accountNumber;
	@Column(name = "last_update_timestamp")
	private LocalDateTime lastUpdateTimestamp;
	@Column(name = "balance")
	private Double balance;
	@Version
	private long version;

}
