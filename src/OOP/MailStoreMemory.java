package OOP;

import java.util.*;

public class MailStoreMemory extends MailStore{
	
	
	
	public MailStoreMemory() {
		super();
	}


	@Override
	public TreeSet<Message> getMail() {
		// TODO Auto-generated method stub
		return this.messages;
	}


	@Override
	public void sendMail(Message m) {
		// TODO Auto-generated method stub
		this.messages.add(m);
	}

}
