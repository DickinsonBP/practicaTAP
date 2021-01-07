package OOP;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.*;
import java.util.function.Predicate;

public class CLI {
	private MailStoreFile mailstore;
	private MailSystem mailsystem;
	private Scanner teclado = new Scanner(System.in);
	private Mailbox mbx;
	
	
	public MailStoreFile getMailstore() {
		return mailstore;
	}

	public void setMailstore(MailStoreFile mailstore) {
		this.mailstore = mailstore;
	}

	public MailSystem getMailsystem() {
		return mailsystem;
	}

	public void setMailsystem(MailSystem mailsystem) {
		this.mailsystem = mailsystem;
	}

	public Mailbox getMbx() {
		return mbx;
	}

	public void setMbx(Mailbox mbx) {
		this.mbx = mbx;
	}

	public CLI() {
		mailstore = new MailStoreFile();
		mailsystem = new MailSystem(mailstore);
	}
	
	public void loadUsers() throws FileNotFoundException, ParseException {
		/*
		 * metodo para cargar los usuarios del sistema
		 */
		TreeSet<Message> m = mailstore.getMail();
		for(Message msg : m) {
			if(!mailsystem.getMailBox().containsKey(msg.getReceiver().getUserName())) {
				mbx = mailsystem.newUser(msg.getReceiver());
				mbx.updateMail();
			}
			if(!mailsystem.getMailBox().containsKey(msg.getSender().getUserName())) {
				mbx = mailsystem.newUser(msg.getSender());
				mbx.updateMail();
			}
		}
	}

	public void logUser(String username) throws ParseException, IOException {
		/*actualizar mensajes al inicar sesion*/
		mbx = mailsystem.getMailBox().get(username);
		mbx.updateMail();
		System.out.println("Hello "+mbx.getUser().getName()+"! :D");
		helpLogUser();
		String estado = teclado.nextLine();
		String []parts = estado.split("'");
		while(!estado.equals("exit")) {
			String []a = parts[0].split(" ");
			if(a[0].equals("send")) {
				/*
				 * enviar mensaje
				 */
				User receiver = mailsystem.getMailBox().get(a[1]).getUser();
				User sender = mailsystem.getMailBox().get(username).getUser();
				Message msg = new Message(parts[1],sender, receiver);
				msg.setBody(parts[3]);
				mailstore.sendMail(msg);
			}else if(a[0].equals("update")) {
				/*
				 * actualizar la lista de mensajes
				 */
				mbx = mailsystem.getMailBox().get(username);
				mbx.updateMail();
				System.out.println(mbx.toString());
			}else if(a[0].equals("list")) {
				/*
				 * 
				 */
				TreeSet<Message> list = new TreeSet<Message>(new AddComparator());
				list.addAll(mbx.filter(p -> p.getReceiver().getUserName().equals(username)));
				System.out.println(list);
			}else if(a[0].equals("order")) {
				/*
				 * ordenar mensajes
				 */
				System.out.println("Menssajes ordenados por fecha:\n"+mbx.getMessageList());
			}else if(a[0].equals("filter")) {
				/*
				 * filtrado de mensajes
				 */
				TreeSet<Message> list = new TreeSet<Message>(new AddComparator());
				String condition = a[1];
				String b = a[2];
				if(condition.equals("contains")) {
					list.addAll(mbx.filter(p -> p.getBody().contains(b)));
					list.addAll(mbx.filter(p -> p.getSubject().contains(b)));
				}else if(condition.equals("lessthan")) {
					int num = Integer.parseInt(b);
					list.addAll(mbx.filter(p -> p.getWords() < num));
				}
				
				System.out.println(list);
			}else helpLogUser();
			 estado = teclado.nextLine();
			 parts = estado.split("'");
		}
		
	}
	
	public void createUser(String userName, String name, int dateBirth) {
		mbx = mailsystem.newUser(new User(userName,name,dateBirth));
	}
	
	public void helpLogUser() {
		System.out.println("send <to> 'subject' 'body'");
		System.out.println("update");
		System.out.println("order <...>");
		System.out.println("filter <...>");
		System.out.println("exit");
	}

	public void helpMenu() {
		System.out.println("createuser <username> <name> <datebirth>");
		System.out.println("filter <...>");
		System.out.println("logas <nombre de usuario>");
		System.out.println("stop");
	}
	
	public void filter() {
		
	}

	public TreeSet<Message> filterAll(String condition,String a) {
		TreeSet<Message> list = new TreeSet<Message>(new AddComparator());
		if(condition.equals("contains")) {
			list.addAll(mailsystem.filterMessages(p -> p.getBody().contains(a)));
			list.addAll(mailsystem.filterMessages(p -> p.getSubject().contains(a)));
		}else if(condition.equals("lessthan")) {
			int num = Integer.parseInt(a);
			list.addAll(mailsystem.filterMessages(p -> p.getWords() < num));
		}
		return list;
	}

}
