package it.unisannio.bank.application;

import javax.ejb.Remote;

import it.unisannio.bank.model.User;

@Remote
public interface BranchRemote {
	public User createUser(String codicefiscale, String nome, String cognome, String email, String psw);
}
