package PATTERNS;

import java.security.NoSuchAlgorithmException;

import javax.crypto.NoSuchPaddingException;

import OOP.Message;
import OOP.User;

public abstract class MessageDecorator extends Message{

	private Message msg;
	
	public MessageDecorator(Message m) {
		super(m.getSubject(),m.getBody(),m.getSender(),m.getReceiver());
		this.msg = m;
	}
	
	public String getBody(){
		return msg.getBody();
	}

}
