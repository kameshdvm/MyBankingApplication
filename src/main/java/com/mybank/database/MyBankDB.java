package com.mybank.database;

import org.springframework.stereotype.Component;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

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
	@NotNull(message="User Name must be Not Null")
	private String userName;
	@Column
	@Size(min=5,message="Password Must Be Minimum 5 Letters")
	private String password;
	
	
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
	
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "MyBankDB [accountNum=" + accountNum + ", accountBal=" + accountBal + ", userName=" + userName
				+ ", Password=" + password + "]";
	}

	
}
