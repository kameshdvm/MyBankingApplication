package com.mybank.database;

import org.springframework.stereotype.Component;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="mybank")
@Component
public class MyBankDB {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer accountNum;
	@Column 
	private Integer accountBal;
	@Column
	private String userName;
	
public Integer getAccountNum() {
		return accountNum;
	}
	public void setAccountNum(Integer accountNum) {
		this.accountNum = accountNum;
	}
	public Integer getAccountBal() {
		return accountBal;
	}
	public void setAccountBal(Integer accountBal) {
		this.accountBal = accountBal;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	@Override
	public String toString() {
		return "MyBankDB [accountNum=" + accountNum + ", accountBal=" + accountBal + ", userName=" + userName + "]";
	}

	
}
