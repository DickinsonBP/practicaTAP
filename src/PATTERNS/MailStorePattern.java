package PATTERNS;
import java.io.*;
import java.text.ParseException;
import java.util.*;

import OOP.*;

public class MailStorePattern extends MailStore{

	
	@Override
	/*
	 * encriptar mensajes
	 */
	public void sendMail(Message m) throws IOException {
		// TODO Auto-generated method stub
		
		BodyDecorator encryptMsg = new BodyDecorator(m);
		m.setBody(encryptMsg.encryptBody());
		
		BufferedWriter fileWriter = new BufferedWriter(new FileWriter("MailStoreEncrypted.txt",true));
		fileWriter.write(m.toString());
		fileWriter.close();
	}

	/*
	 * desencriptar mensajes
	 */
	@Override
	public TreeSet<Message> getAllMessages() throws FileNotFoundException, ParseException {
		Scanner file = new Scanner(new FileReader("MailStoreEncrypted.txt"));

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

			Message message = new Message(data[2],null,sender,receiver);
			BodyDecorator bodyDecorator = new BodyDecorator(message);
			message.setTime(time);
			message.setBody(bodyDecorator.decryptBody(data[3]));
			this.messages.add(message);
		}

		file.close();
		return this.messages;
	}

	@Override
	public TreeSet<Message> getUserMessages(String username) throws FileNotFoundException, ParseException {
		return null;
	}

	@Override
	public TreeSet<User> getAllUsers() throws FileNotFoundException, ParseException {
		return null;
	}



	
}
