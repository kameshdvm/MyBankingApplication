package com.mybank.database;

import org.springframework.stereotype.Component;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="mybank")
@Component
public class MyBankDB {
	
	@Id
	private Integer accountNum=null;
	@Column 
	private Integer accountBal=null;
	@Column
	private String userName=null;
	
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

	
}
