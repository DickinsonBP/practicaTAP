package OOP;

import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.*;
import java.util.function.Predicate;

public class MailSystem {
	
	private MailStore mailStore;
	private HashMap<String,Mailbox> mailBox;
	
	public MailSystem(MailStore mailStore) {
		this.mailStore = mailStore;
		mailBox = new HashMap<String,Mailbox>();
	}
	
	/*
	 * rutina para a�adir un nuevo usuario al sistema, el usuario se a�ade a la lista de usuarios y se genera una mailbox para el
	 */
	public Mailbox newUser(User user) {
		Mailbox mbx = new Mailbox(user,this.mailStore);
		mailBox.put(user.getUserName(), mbx);
		
		return mbx;
	}
	
	
	/*
	 * rutina que devuelve todos los mensajes que hay en el sistema
	 */
	public TreeSet<Message> allSystemMessages() throws FileNotFoundException, ParseException {
		return this.mailStore.getAllMessages();
	}
	
	
	/*
	 * rutina que filtra todos los mensajes por una condicion
	 */
	public TreeSet<Message> filterMessages(Predicate<Message> pred) {
		TreeSet<Message> list = new TreeSet<Message>(new AddComparator());
		
		for(Mailbox mbx : this.mailBox.values()) {
			list.addAll((ArrayList<Message>) mbx.filter(pred));
		}
		
		return list;
    }

	/*
	 * rutina que devuelve el numero total de mensajes en el sistema
	 */
	public int totalMessages() throws FileNotFoundException, ParseException {
		return this.mailStore.getAllMessages().size();
	}
	
	/*
	 * rutina que devuelve el promedio de mensajes por usuario
	 */
	public double averageUserMessage() {
		double cont = 0;
		for(Mailbox i : this.mailBox.values()) {
			cont += i.getMessageList().size();
		}
		return (cont/this.mailBox.size());
	}
	
	/*
	 * rutina que agrupa los mensajes por asunto de cualquier usuario
	 */
	public TreeSet<Message> groupMessages(String subject) {
		Predicate<Message> pred = p -> p.getSubject() == subject;
		return this.filterMessages(pred);
	}
	
	/*
	 * rutina que cuenta las palabras de todos los mensajes de un usario en particular
	 */
	public int countWords(String name) throws FileNotFoundException, ParseException {
		int cont = 0;
		for(Message i : this.mailStore.getUserMessages(name)) {
			String[] message = i.getBody().split(" ");
			cont += message.length;
		}
		return cont;
	}
	
	/*
	 * rutina para recivir mensajes para usuarios nacidos antes de cierto a�o
	 */
	public TreeSet<Message> reciveMessages(int year){
		Predicate<Message> pred = p -> p.getReceiver().getDateBirth() > year;
		return this.filterMessages(pred);
	}
	
	public MailStore getMailStore() {
		return mailStore;
	}

	public void setMailStore(MailStore mailStore) {
		this.mailStore = mailStore;
	}

	public HashMap<String,Mailbox> getMailBox() {
		return mailBox;
	}

	public void setMailBox(HashMap<String,Mailbox> mailBox) {
		this.mailBox = mailBox;
	}

	
}
