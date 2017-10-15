package it.unisannio.bank.application;

import java.util.ArrayList;

import javax.ejb.Remote;

import it.unisannio.bank.model.Account;
import it.unisannio.bank.model.User;

@Remote
public interface BranchRemote {
	public User createUser(String codicefiscale, String nome, String cognome, String email, String psw);
	public User getUser(String email);
	public User getUserByApi_key(String api_key);
	public ArrayList<Account> getUserAccounts(String email);
	public Account createAccount(String email);
	public Account withdraw(double value, int idAccount);
	public ArrayList<Account> transfer(int source, int destination, double value);
	public Account deposit(double value,int accountid);
	public Account getAccount(int accountid);


}
