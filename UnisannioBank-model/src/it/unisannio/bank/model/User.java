package it.unisannio.bank.model;

import java.io.Serializable;
import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@SuppressWarnings("serial")
@Entity
@NamedQueries({	@NamedQuery(name = "findAllUsers", query = "SELECT u FROM User u "),
				@NamedQuery(name = "getUserByApiKey", query = "SELECT u FROM User u WHERE u.api_key = :api_key")} )
public class User implements Serializable {

//	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	private String email;
	private String nome, cognome, codicefiscale, api_key, password;

	private ArrayList<Account> accounts;
	
	public User() {
		// TODO Auto-generated constructor stub
		super();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getCodicefiscale() {
		return codicefiscale;
	}

	public void setCodicefiscale(String codicefiscale) {
		this.codicefiscale = codicefiscale;
	}

	public String getApi_key() {
		return api_key;
	}

	public void setApi_key(String api_key) {
		this.api_key = api_key;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public ArrayList<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(ArrayList<Account> accounts) {
		this.accounts = accounts;
	}

	@Override
	public String toString() {
		return "User [email=" + email + ", nome=" + nome + ", cognome=" + cognome + ", codicefiscale=" + codicefiscale
				+ ", api_key=" + api_key + ", password=" + password + ", accounts=" + accounts + "]";
	}




	
	
	
	

}
