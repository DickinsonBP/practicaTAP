package OOP;

import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.*;
import java.util.logging.Filter;

public class MailStoreMemory extends MailStore{
	
	
	
	public MailStoreMemory() {
		super();
	}

	@Override
	public void sendMail(Message m) {
		// TODO Auto-generated method stub
		this.messages.add(m);
	}

	@Override
	public TreeSet<Message> getAllMessages() throws FileNotFoundException, ParseException {
		return this.messages;
	}

	@Override
	public TreeSet<Message> getUserMessages(String username) {
		TreeSet<Message> userMessage = new TreeSet<Message>(new AddComparator());

		userMessage.stream().filter(p -> p.getReceiver().getUserName().equals(username));

		return  userMessage;
	}

	@Override
	public TreeSet<User> getAllUsers() throws FileNotFoundException, ParseException {
		TreeSet<User> allUsers = new TreeSet<User>();

		for(Message msg : this.getAllMessages()){
			allUsers.add(msg.getSender());
			allUsers.add(msg.getReceiver());
		}
		return allUsers;
	}

}
