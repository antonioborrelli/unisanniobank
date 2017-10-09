package it.unisannio.bank.utility;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Converter {

	public Converter() {
		// TODO Auto-generated constructor stub
	}

	public static String getMD5(String string){
		MessageDigest m;
        
		try {
			m = MessageDigest.getInstance("MD5");
			m.update(string.getBytes());
			return String.format("%032x",new BigInteger(1,m.digest()));
	    
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			return null;
		}
	}
}
