package it.unisannio.bank.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

@SuppressWarnings("serial")
@Entity
@NamedQuery(name = "findAllUsers", query = "SELECT u from User u ")
public class User implements Serializable {

//	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	private String userId;
	private String nome, cognome, email, api_key,password;

	public User() {
		// TODO Auto-generated constructor stub
		super();
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	@Override
	public String toString() {
		return "User [userId=" + userId + ", nome=" + nome + ", cognome=" + cognome + ", email=" + email + ", api_key="
				+ api_key + ", password=" + password + "]";
	}

	
	
	
	

}
