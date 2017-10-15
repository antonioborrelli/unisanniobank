package it.unisannio.bank.application;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import it.unisannio.bank.interceptor.Interceptor;
import it.unisannio.bank.model.Account;
import it.unisannio.bank.model.User;

@Stateless
public class Branch implements BranchRemote, BranchLocal{

	@PersistenceContext
	EntityManager entityManager;
	
	public Branch() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public User createUser(String codicefiscale, String nome, String cognome, String email, String psw) {
		// TODO Auto-generated method stub
		User user = new User();
		user.setNome(nome);
		user.setCognome(cognome);
		user.setEmail(email);
		user.setApi_key(getApi_key(codicefiscale));
		user.setCodicefiscale(codicefiscale);
		user.setPassword(psw);
		entityManager.persist(user);
		return user;
	}

	@Override
	public User getUserByApi_key(String api_key) {
		// TODO Auto-generated method stub
		List<User> results = entityManager.createNamedQuery("getUserByApiKey", User.class).setParameter("api_key", api_key)
				.getResultList();
		if(results.size() > 0 )
			return results.get(0);
		return null;
	}

	@Override
	public User getUser(String email) {
		// TODO Auto-generated method stub	
		return entityManager.find(User.class, email);
	}
	
	@Override
	public ArrayList<Account> getUserAccounts(String email) {
		// TODO Auto-generated method stub
		List<Account> list = entityManager.createNamedQuery("getAccountsByEmail", Account.class).setParameter("email", email)
				.getResultList();

		return (ArrayList<Account>) list;
	}
	
	@Override
	public Account createAccount(String email) {
		// TODO Auto-generated method stub
		
		Account account = new Account();					// Creo un oggetto account vuoto
		account.setEmail(email);							// aggiungo il cliente al conto
		User user = entityManager.find(User.class, email);	// richiamo i dati del cliente dall'db
		user.addAccount(account);							// aggiungo il conto creato al cliente
		entityManager.persist(user);						// salvo nel db il nuovo conto

		return account;		// effettuo il primo deposito
		
		
//		Account account = new Account();					// Creo un oggetto account vuoto
//		account.setEmail(email);							// aggiungo il cliente al conto
//		User user = entityManager.find(User.class, email);	// richiamo i dati del cliente dall'db
//		user.addAccount(account);							// aggiungo il conto creato al cliente
//		entityManager.persist(user);						// salvo nel db il nuovo conto
//
//		return deposit(amount, account.getAccountId());		// effettuo il primo deposito
	}

	@Override
	@Interceptors(Interceptor.class)	
	public Account deposit(double value,int accountid) {
		// TODO Auto-generated method stub
		Account account = entityManager.find(Account.class, accountid);
		if(account!=null) {
			double total=value+account.getBalance();
			account.setBalance(total);
			entityManager.persist(account);
			return account;
			
		}
		return null;
	}

	@Override
	@Interceptors(Interceptor.class)	
	public Account withdraw(double value, int idAccount){
		
		Account account = entityManager.find(Account.class, idAccount);
		if(account != null){
			double total = account.getBalance() - value;
			if(total >=0){
				account.setBalance(total);
				entityManager.persist(account);
				return account;
			}
		}
		
		return null;
	}
	
	@Override
	@Interceptors(Interceptor.class)	
	public ArrayList<Account>  transfer(int source, int destination, double value) {
		ArrayList<Account> list = new ArrayList<>();
		Account sourceAccount= entityManager.find(Account.class, source);
		Account sourceDest= entityManager.find(Account.class, destination);
		if(sourceAccount!=null && sourceDest!= null && sourceAccount.getBalance()>= value) {
			
			sourceAccount.setBalance(sourceAccount.getBalance()-value);
			sourceDest.setBalance(sourceDest.getBalance()+value);
			
			entityManager.persist(sourceAccount);
			entityManager.persist(sourceDest);
			list.add(sourceAccount);
			list.add(sourceDest);
			return list;
			
		}//end if primo
		
		return null;
	}

	// Generatore di api_key utilizzato come cookie
	private String getApi_key(String codicefiscale){
		Date date = new Date();
		String api_key = date.toString() + " " + codicefiscale;
		
        MessageDigest m;
        
		try {
			m = MessageDigest.getInstance("MD5");
			m.update(api_key.getBytes());
			return String.format("%032x",new BigInteger(1,m.digest()));
	    
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			return null;
		}
		
	}

	@Override
	public Account getAccount(int accountid) {
		// TODO Auto-generated method stub
		return entityManager.find(Account.class, accountid);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@Override
	public double totalAmount() {
		
		List<Account> accounts= entityManager.createNamedQuery("getTotalAmount", Account.class).getResultList();
		double total=0; 
		for(int i=0; i< accounts.size(); i++)
		{
			total += accounts.get(i).getBalance();
		}
		return total;
	}// end totalAmount

	
	
	@Override
	public double totalAmountUser(String api_key) {
		User user = entityManager.createNamedQuery("getUserByApiKey", User.class).setParameter("api_key", api_key).getSingleResult();
		
		
		List<Account> accounts= user.getAccounts();
		double total=0; 
		for(int i=0; i< accounts.size(); i++)
		{
			total += accounts.get(i).getBalance();
		}
		return total;
		
	}

}
