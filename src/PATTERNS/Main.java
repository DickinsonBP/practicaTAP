package PATTERNS;
import java.io.*;
import java.text.*;
import java.util.*;

import OOP.*;


public class Main {
	
	public static void main(String[] args) throws IOException, ParseException {
		MailStore mailstore = new MailStoreMemory();
		MailSystem mailsystem = new MailSystem(mailstore);
		
		User u1 = new User("dickinsonbp","Dickinson Bedoya Perez",2000);
		User u2 = new User("anna.gracia","Anna Gracia Colmenarejo",2000);
		User u3 = new User("spam:ainhoabp","Ainhoa Bedoya Perez",2011);
		
		mailsystem.newUser(u1);
		mailsystem.newUser(u2);
		mailsystem.newUser(u3);
		
		MailboxPattern mbx1 = new MailboxPattern(u1,mailstore);
		mbx1.attach(new SpamUserFilter());
		mbx1.attach(new TooLongFilter());
		
		
		Message msg1 = new Message("Nueva oferta de jamon!!!!",u3,u1);
		msg1.setBody("Tenemos jamones de oferta para la navidad!!!!!");
		mailstore.sendMail(msg1);
		Message msg2 = new Message("Hola",u1,u2);
		msg2.setBody("Feliz navidad hola hola hola hola!!!");
		mailstore.sendMail(msg2);
		Message msg3 = new Message("Respuesta",u2,u1);
		msg3.setBody("Igualmente!!");
		mailstore.sendMail(msg3);
		Message msg4 = new Message("Neveras a buen precio!!!!",u3,u1);
		msg4.setBody("Tenemos neveras a buen precio estas navidades!!!");
		mailstore.sendMail(msg4);
		
		mbx1.updateMail();
		mbx1.setMailboxPattern();
		System.out.println(mbx1.getSpam());
		System.out.println(mbx1.getTooLong());
		System.out.println(mbx1.getMessageList());
		System.out.println(mbx1.showSpamUsers());
	}

}
