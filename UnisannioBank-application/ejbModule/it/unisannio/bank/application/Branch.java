package it.unisannio.bank.application;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
		
		
		System.out.println( codicefiscale +" "+ nome+" "+cognome+" "+email+" "+psw);
		User user = new User();
		user.setNome(nome);
		user.setCognome(cognome);
		user.setEmail(email);
		user.setApi_key("0000000000");
		user.setUserId(codicefiscale);
		user.setPassword(psw);
		System.out.println(user.toString());
		entityManager.persist(user);
		return user;
	}



}
