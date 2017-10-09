package it.unisannio.bank.application;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
	public Account createAccount(String email, double amount) {
		// TODO Auto-generated method stub
		Account account = new Account();
		account.setEmail(email);
		account.setBalance(amount);
		entityManager.persist(account);
		return account;
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





}
