package it.unisannio.bank.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

@SuppressWarnings("serial")
@Entity
@NamedQueries({	@NamedQuery(name = "findAllAmounts", query = "SELECT a FROM Account a "),
				@NamedQuery(name = "getAccountsByEmail", query = "SELECT a FROM Account a WHERE a.email = :email "),
				@NamedQuery(name = "getTotalAmount", query = "SELECT a FROM Account a  WHERE a.balance > 0"),
				})
public class Account implements Serializable {

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private int accountId;
	private double balance;
	private String email;
	@OneToMany(cascade = CascadeType.ALL)
	private List<Log> logs;
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

	public void addLog(Log log) {
		this.logs.add(log);
	}
	
	public List<Log> getLogs() {
		return logs;
	}

	public void setLogs(List<Log> logs) {
		this.logs = logs;
	}

	@Override
	public String toString() {
		return "Account [accountId=" + accountId + ", balance=" + balance + ", email=" + email + "]";
	}
	

	
}