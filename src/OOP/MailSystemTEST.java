package OOP;

import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;

import org.junit.Test;

import junit.framework.TestCase;

public class MailSystemTEST extends TestCase{
	/*
	 * clase para probar el funcionamiento del mailsystem 
	 */
	private static MailStoreFile mailstore = new MailStoreFile();
	private static MailSystem mailsystem = new MailSystem(mailstore);
	
	@Test
	public void testcrearUsuario(){
		User u1 = new User("dickinsonbp","Dickinson",2000);
		/*User u2 = new User("ainhoa","Ainhoa Bedoya Perez",2011);
		User u3 = new User("paulap","Paula Perez Ospina",1976);*/
		
		Mailbox mbx = mailsystem.newUser(u1);
		assertEquals(mbx.getUser(),u1);
	}
	
	@Test
	public void testEnviarMensaje() throws IOException, ParseException {
		User u1 = new User("dickinsonbp","Dickinson",2000);
		User u2 = new User("ainhoa","Ainhoa",2011);
		Mailbox mbx1 = mailsystem.newUser(u1);
		Mailbox mbx2 = mailsystem.newUser(u2);
		
		Message msg = new Message("hola",u1,u2);
		msg.setBody("Esto es un mensaje de prueba");
		
		mailstore.sendMail(msg);
		mbx2.updateMail();
		Message m = mbx2.getMessageList().first();
		u1 = m.getReceiver();
		assertEquals(u2.getName(), u1.getName());
	}
	
	@Test
	public void testCuentaMensajes() {
		
	}
}
