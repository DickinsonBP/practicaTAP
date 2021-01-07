package PATTERNS;

import java.security.NoSuchAlgorithmException;
import java.util.*;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;


import OOP.*;

public class BodyDecorator extends MessageDecorator{
	private Message msg;
	private String key = "IWantToPassTAP12"; // 128 bit key
	private java.security.Key aesKey =
			new javax.crypto.spec.SecretKeySpec(key.getBytes(), "AES");
	
	
	public BodyDecorator(Message m) {
		super(m);
		this.msg = m;
	}
	
	public Cipher CreateCipher() {
		Cipher cipher = null;
		try {
			cipher = Cipher.getInstance("AES");
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cipher;
	}
	
	
	public String encryptBody() {
		Cipher cipher = CreateCipher();
		
		byte[] encrypted = new byte[0];
		try {
		cipher.init(Cipher.ENCRYPT_MODE, aesKey);
		encrypted = cipher.doFinal(msg.getBody().getBytes());
		} catch (Exception e) {
		e.printStackTrace();
		}
		return Base64.getEncoder().encodeToString(encrypted);
	}
	
	
	public String decryptBody(String body) {
		Cipher cipher = CreateCipher();
		byte[] encrypted = Base64.getDecoder().decode(body.getBytes());
		String decrypted = null;
		try {
		cipher.init(Cipher.DECRYPT_MODE, aesKey);
		decrypted = new String(cipher.doFinal(encrypted));
		} catch (Exception e) {
		e.printStackTrace();
		}
		return decrypted;
	}
}
