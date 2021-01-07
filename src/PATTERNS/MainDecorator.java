package PATTERNS;

import java.io.IOException;
import java.text.ParseException;

import OOP.*;

public class MainDecorator {

	public static void main(String[] args) throws IOException, ParseException {
		MailStore mailstore = new MailStorePattern();
		MailSystem mailsystem = new MailSystem(mailstore);
		
		User u1 = new User("dickinsonbp","Dickinson Bedoya Perez",2000);
		User u2 = new User("anna.gracia","Anna Gracia Colmenarejo",2000);
		User u3 = new User("ainhoabp","Ainhoa Bedoya Perez",2011);
		
		mailsystem.newUser(u1);
		mailsystem.newUser(u2);
		mailsystem.newUser(u3);
		
		MailboxPattern mbx1 = new MailboxPattern(u1,mailstore);
		mbx1.attach(new SpamUserFilter());
		mbx1.attach(new TooLongFilter());
		MailboxPattern mbx2 = new MailboxPattern(u2,mailstore);
		mbx2.attach(new SpamUserFilter());		MailboxPattern mbx3 = new MailboxPattern(u3,mailstore);
		mbx3.attach(new SpamUserFilter());

		Message msg1 = new Message("Nueva oferta de jamon!!!!","Tenemos jamones de oferta para la navidad!!!!!",u3,u1);
		mailstore.sendMail(msg1);
		Message msg2 = new Message("Hola","Feliz navidad hola hola hola hola!!!",u1,u2);
		mailstore.sendMail(msg2);
		Message msg3 = new Message("Respuesta","Igualmente!!",u2,u1);
		mailstore.sendMail(msg3);
		Message msg4 = new Message("Neveras a buen precio!!!!","Tenemos neveras a buen precio estas navidades!!!",u3,u1);
		mailstore.sendMail(msg4);
		
		
		mbx1.updateMail();
		System.out.println(mbx1.getMessageList());
	}

}
