package it.unisannio.bank.application;

import java.util.ArrayList;

import javax.ejb.Local;

import it.unisannio.bank.model.Account;
import it.unisannio.bank.model.User;

@Local
public interface BranchLocal {
	

	
	public User createUser(String codicefiscale, String nome, String cognome, String email, String psw);
	public ArrayList<Account> getUserAccounts(String email);
//	public double totalUserAmount(String codicefiscale);
//	public User updateUser(String codicefiscale, String nome, String cognome, String email, String psw);
	public User getUser(String email);
	public User getUserByApi_key(String api_key);
	
	public double totalAmount();
	public double totalAmountUser(String api_key);
	public Account getAccount(int accountid);
	public Account createAccount(String email);
	public Account deposit(double value,int accountid);
	public Account withdraw(double value, int idAccount);
//	public void withdraw(double value);
	public ArrayList<Account> transfer(int source, int destination, double value);
//	
}

