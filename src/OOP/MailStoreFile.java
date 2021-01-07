package OOP;

import java.io.*;
import java.text.ParseException;
import java.util.*;

public class MailStoreFile extends MailStore{


	public MailStoreFile() {
		super();
	}


	@Override
	public void sendMail(Message m) throws IOException {
		// TODO Auto-generated method stub
		BufferedWriter fileWriter = new BufferedWriter(new FileWriter("MailStore.txt",true));
		fileWriter.write(m.toString());
		fileWriter.close();
	}

	@Override
	public TreeSet<Message> getAllMessages() throws FileNotFoundException, ParseException {
		Scanner file = new Scanner(new FileReader("MailStore.txt"));

		while(file.hasNext()) {

			String line = file.nextLine();
			String[] data = line.split(";");
			Tiempo time = new Tiempo();

			time.setDate(data[0]);
			String usuario = data[1];
			String[] user = usuario.split("-");

			User sender = new User(user[0],user[1],Integer.parseInt(user[2]));

			usuario = data[4];
			user = usuario.split("-");
			User receiver = new User(user[0],user[1],Integer.parseInt(user[2]));

			Message message = new Message(data[2],data[3],sender,receiver);
			message.setTime(time);
			this.messages.add(message);
		}

		file.close();
		return this.messages;
	}

	@Override
	public TreeSet<Message> getUserMessages(String username) throws FileNotFoundException, ParseException {
		TreeSet<Message> userMessage = new TreeSet<Message>(new AddComparator());
		Scanner file = new Scanner(new FileReader("MailStore.txt"));

		while(file.hasNext()) {

			String line = file.nextLine();
			String[] data = line.split(";");
			Tiempo time = new Tiempo();

			time.setDate(data[0]);
			String usuario = data[1];
			String[] user = usuario.split("-");

			User sender = new User(user[0],user[1],Integer.parseInt(user[2]));

			usuario = data[4];
			user = usuario.split("-");
			User receiver = new User(user[0],user[1],Integer.parseInt(user[2]));

			Message message = new Message(data[2],data[3],sender,receiver);
			message.setTime(time);
			if(receiver.getUserName().equals(username)){
				userMessage.add(message);
			}
		}

		file.close();


		return userMessage;
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
