package it.unisannio.bank.application;

import javax.ejb.Local;

import it.unisannio.bank.model.User;

@Local
public interface BranchLocal {
	

	
	public User createUser(String codicefiscale, String nome, String cognome, String email, String psw);
//	public ArrayList<Account> getUserAccounts(String codicefiscale);
//	public double totalUserAmount(String codicefiscale);
//	public User updateUser(String codicefiscale, String nome, String cognome, String email, String psw);
//	public User getUser(String codicefiscale);
//	public User loginUser(String email, String psw);
	
	
//	public double totalAmount();
//	public int createAccount(String userId, double amount);
//	public void deposit(double value);
//	public void withdraw(double value);
//	public void transfer(int source, int destination, double value);
//	
}
