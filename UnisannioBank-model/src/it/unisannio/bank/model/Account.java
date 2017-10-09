package it.unisannio.bank.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@SuppressWarnings("serial")
@Entity
@NamedQueries({	@NamedQuery(name = "findAllAmounts", query = "SELECT a FROM Account a "),
				@NamedQuery(name = "getAccountsByEmail", query = "SELECT a FROM Account a WHERE a.email = :email ")})
public class Account implements Serializable {

	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	private int accountId;
	private double balance;
	private String email;

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Account [accountId=" + accountId + ", balance=" + balance + ", email=" + email + "]";
	}
	

	
}