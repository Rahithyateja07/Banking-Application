package com.example.demo.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "accounts")
public class Accounts {
	
	@Id
    private int customerID;
	String password;
	@Column(unique = true)
    private int accountNumber;
    private String ifsc;
    private String accountType;
    private int mobile;
    private double balance;
	public Accounts() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Accounts(int customerID, String password, int accountNumber, String ifsc, String accountType, int mobile,
			double balance) {
		super();
		this.customerID = customerID;
		this.password = password;
		this.accountNumber = accountNumber;
		this.ifsc = ifsc;
		this.accountType = accountType;
		this.mobile = mobile;
		this.balance = balance;
	}
	public int getCustomerID() {
		return customerID;
	}
	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getIfsc() {
		return ifsc;
	}
	public void setIfsc(String ifsc) {
		this.ifsc = ifsc;
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public int getMobile() {
		return mobile;
	}
	public void setMobile(int mobile) {
		this.mobile = mobile;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	@Override
	public String toString() {
		return "Accounts [customerID=" + customerID + ", password=" + password + ", accountNumber=" + accountNumber
				+ ", ifsc=" + ifsc + ", accountType=" + accountType + ", mobile=" + mobile + ", balance=" + balance
				+ "]";
	}
	
    
    

}
