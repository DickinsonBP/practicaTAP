package OOP;

import com.sun.source.tree.Tree;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.*;

public abstract class MailStore {
	protected TreeSet<Message> messages;
	
	public MailStore() {
		messages = new TreeSet<Message>(new AddComparator());
	}
	
	/*
	 * rutina para enviar correo nuveo a un usuario
	 */
	public abstract void sendMail (Message m) throws IOException;
	
	/*
	 * rutina para obtener correos destinados a un usuario
	 */
	public abstract TreeSet<Message> getAllMessages () throws FileNotFoundException, ParseException;
	

	public abstract TreeSet<Message> getUserMessages(String username) throws FileNotFoundException, ParseException;


	public void setMessages(TreeSet<Message> messages) {
		this.messages = messages;
	}

	public abstract TreeSet<User> getAllUsers() throws FileNotFoundException, ParseException;
}
