package OOP;

import java.io.*;
import java.text.ParseException;
import java.util.*;

public class MailStoreFile extends MailStore{


	public MailStoreFile() {
		super();
	}

	@Override
	public TreeSet<Message> getMail() throws FileNotFoundException, ParseException {
		// TODO Auto-generated method stub
		//HashSet<Message> list = new HashSet<Message>();
		
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

			Message message = new Message(data[2],sender,receiver);
			message.setTime(time);
			message.setBody(data[3]);
			//boolean existe = this.messages.contains(message);
			//if(!this.messages.contains(message))this.messages.add(message);
			this.messages.add(message);
		}
		
		file.close();
		return this.messages;
	}

	@Override
	public void sendMail(Message m) throws IOException {
		// TODO Auto-generated method stub
		BufferedWriter fileWriter = new BufferedWriter(new FileWriter("MailStore.txt",true));
		fileWriter.write(m.toString());
		fileWriter.close();
	}

}
