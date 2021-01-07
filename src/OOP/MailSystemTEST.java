package OOP;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;
import java.util.TreeSet;
import java.util.function.Predicate;

import PATTERNS.MailStorePattern;
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
		
		Mailbox mbx = mailsystem.newUser(u1);
		assertEquals(mbx.getUser(),u1);
	}

	@Test
	public void testAllSystemMessages() throws IOException, ParseException {
		MailStore mailstore = new MailStoreMemory();
		MailSystem mailsystem = new MailSystem(mailstore);
		User u1 = new User("dickinsonbp","Dickinson Bedoya Perez",2000);
		User u2 = new User("anna.gracia","Anna Gracia Colmenarejo",2000);
		mailsystem.newUser(u1);
		mailsystem.newUser(u2);

		Message msg1 = new Message("Nueva oferta de jamon!!!!","Tenemos jamones de oferta para la navidad!!!!!",u2,u1);
		mailstore.sendMail(msg1);
		Message msg2 = new Message("Hola","Feliz navidad hola hola hola hola!!!",u1,u2);
		mailstore.sendMail(msg2);
		TreeSet<Message> expected = new TreeSet<Message>(new AddComparator());
		expected.add(msg1);
		expected.add(msg2);

		TreeSet<Message> result = mailsystem.allSystemMessages();

		assertEquals(result,expected);
	}


	@Test
	public void testFilerMessages() throws IOException, ParseException {
		//filer by username
		MailStore mailstore = new MailStoreMemory();
		MailSystem mailsystem = new MailSystem(mailstore);
		User u1 = new User("dickinsonbp","Dickinson Bedoya Perez",2000);
		User u2 = new User("anna.gracia","Anna Gracia Colmenarejo",2000);
		Mailbox mbx1 = mailsystem.newUser(u1);
		Mailbox mbx2 = mailsystem.newUser(u2);

		Message msg1 = new Message("Nueva oferta de jamon!!!!","Tenemos jamones de oferta para la navidad!!!!!",u2,u1);
		mailstore.sendMail(msg1);
		Message msg2 = new Message("Hola","Feliz navidad hola hola hola hola!!!",u1,u2);
		mailstore.sendMail(msg2);


		TreeSet<Message> expected = new TreeSet<Message>(new AddComparator());
		expected.add(msg2);//unico mensje enviado por dickinsonbp

		mbx1.updateMail();
		mbx2.updateMail();

		Predicate<Message> pred = p -> p.getSender().equals(u1);
		TreeSet<Message> result = mailsystem.filterMessages(pred);

		assertEquals(result,expected);

	}

	@Test
	public void testTotalMessages() throws IOException, ParseException {
		MailStore mailstore = new MailStoreMemory();
		MailSystem mailsystem = new MailSystem(mailstore);
		User u1 = new User("dickinsonbp","Dickinson Bedoya Perez",2000);
		User u2 = new User("anna.gracia","Anna Gracia Colmenarejo",2000);
		Mailbox mbx1 = mailsystem.newUser(u1);
		Mailbox mbx2 = mailsystem.newUser(u2);
		Message msg1 = new Message("Nueva oferta de jamon!!!!","Tenemos jamones de oferta para la navidad!!!!!",u2,u1);
		mailstore.sendMail(msg1);
		Message msg2 = new Message("Hola","Feliz navidad hola hola hola hola!!!",u1,u2);
		mailstore.sendMail(msg2);

		mbx1.updateMail();
		mbx2.updateMail();

		int expected = 2;
		int result = mailsystem.totalMessages();
		assertEquals(result,expected);
	}

	@Test
	public void testAverageUserMessage() throws IOException, ParseException {
		MailStore mailstore = new MailStoreMemory();
		MailSystem mailsystem = new MailSystem(mailstore);
		User u1 = new User("dickinsonbp","Dickinson Bedoya Perez",2000);
		User u2 = new User("anna.gracia","Anna Gracia Colmenarejo",2000);
		Mailbox mbx1 = mailsystem.newUser(u1);
		Mailbox mbx2 = mailsystem.newUser(u2);

		Message msg1 = new Message("Nueva oferta de jamon!!!!","Tenemos jamones de oferta para la navidad!!!!!",u2,u1);
		mailstore.sendMail(msg1);
		Message msg2 = new Message("Hola","Feliz navidad hola hola hola hola!!!",u1,u2);
		mailstore.sendMail(msg2);

		mbx1.updateMail();
		mbx2.updateMail();

		double expected = 2.0;
		double result = mailsystem.averageUserMessage();
		assertEquals(result,expected);

	}

	@Test
	public void testGropuMessages() throws IOException, ParseException {
		MailStore mailstore = new MailStoreMemory();
		MailSystem mailsystem = new MailSystem(mailstore);
		User u1 = new User("dickinsonbp","Dickinson Bedoya Perez",2000);
		User u2 = new User("anna.gracia","Anna Gracia Colmenarejo",2000);
		Mailbox mbx1 = mailsystem.newUser(u1);
		Mailbox mbx2 = mailsystem.newUser(u2);

		Message msg1 = new Message("Hola","Tenemos jamones de oferta para la navidad!!!!!",u2,u1);
		mailstore.sendMail(msg1);
		Message msg2 = new Message("Hola","Feliz navidad hola hola hola hola!!!",u1,u2);
		mailstore.sendMail(msg2);

		mbx1.updateMail();
		mbx2.updateMail();

		TreeSet<Message> expected = new TreeSet<Message>(new AddComparator());
		expected.add(msg1);
		expected.add(msg2);

		TreeSet<Message> result = mailsystem.groupMessages("Hola");

		assertEquals(result,expected);
	}

	@Test
	public void testCountWords() throws IOException, ParseException {
		MailStore mailstore = new MailStoreMemory();
		MailSystem mailsystem = new MailSystem(mailstore);
		User u1 = new User("dickinsonbp","Dickinson Bedoya Perez",2000);
		User u2 = new User("anna.gracia","Anna Gracia Colmenarejo",2000);
		Mailbox mbx1 = mailsystem.newUser(u1);
		Mailbox mbx2 = mailsystem.newUser(u2);

		Message msg1 = new Message("Hola","Tenemos jamones de oferta para la navidad!!!!!",u2,u1);
		mailstore.sendMail(msg1);

		mbx1.updateMail();

		int expected = 7;
		int result = mailsystem.countWords(u1.getUserName());
		assertEquals(result,expected);
	}

	@Test
	public void testReciveMessages() throws IOException, ParseException {
		MailStore mailstore = new MailStoreMemory();
		MailSystem mailsystem = new MailSystem(mailstore);
		User u1 = new User("dickinsonbp","Dickinson Bedoya Perez",2000);
		User u2 = new User("anna.gracia","Anna Gracia Colmenarejo",2000);
		Mailbox mbx1 = mailsystem.newUser(u1);
		Mailbox mbx2 = mailsystem.newUser(u2);

		Message msg1 = new Message("Hola","Tenemos jamones de oferta para la navidad!!!!!",u2,u1);
		mailstore.sendMail(msg1);
		Message msg2 = new Message("Hola","Feliz navidad hola hola hola hola!!!",u1,u2);
		mailstore.sendMail(msg2);

		mbx1.updateMail();
		mbx2.updateMail();

		TreeSet<Message> expected = new TreeSet<Message>(new AddComparator());
		expected.add(msg1);
		expected.add(msg2);

		TreeSet<Message> result = mailsystem.reciveMessages(1998);

		assertEquals(result,expected);
	}

}
