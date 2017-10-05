package it.unisannio.bank.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

@SuppressWarnings("serial")
@Entity
@NamedQuery(name = "findAllAmounts", query = "SELECT a from Account a ") 
public class Account implements Serializable {

	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	private int accountId;
	private double balance;
	private String userId;

	public Account() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "Account [accountId=" + accountId + ", balance=" + balance + ", userId=" + userId + "]";
	}

}
