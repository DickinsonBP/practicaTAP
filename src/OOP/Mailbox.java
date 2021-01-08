package OOP;

import java.io.*;
import java.text.*;
import java.util.*;
import java.util.function.*;
import java.util.stream.*;

public class Mailbox implements Iterable<Message>{
    private User user;
    private TreeSet<Message> messageList;
    private MailStore mailStore;
    
    public Mailbox(User user, MailStore mailStore) {
    	this.user = user;
    	this.messageList = new TreeSet<Message>(new AddComparator());
    	this.mailStore = mailStore;
    }
    

	public void updateMail() throws FileNotFoundException, ParseException {
    	TreeSet<Message> m = mailStore.getAllMessages();
    	
    	
    	for(Message msg : m) {
    		
    		//if(!this.messageList.contains(msg)) {
    			if(msg.getSender().getUserName().equals(user.getUserName())) {
        			this.messageList.add(msg);
        		}
        		if(msg.getReceiver().getUserName().equals(user.getUserName())) {
        			this.messageList.add(msg);
        		}
    		//}
    		
    	}
    	
    	/*ordernar lista de mensajes*/
    	/*AddComparator a = new AddComparator();
    	Collections.sort(this.messageList, a);*/
    }

	@Override
	public String toString() {
		return "messageList=" + messageList + "\n";
	}


	public void setMessageList(TreeSet<Message> messageList) {
		this.messageList = messageList;
	}


	public MailStore getMailStore() {
		return mailStore;
	}


	public void setMailStore(MailStore mailStore) {
		this.mailStore = mailStore;
	}


	public void sendMail(Message m) throws IOException {
		
    	this.mailStore.sendMail(m);
    	this.messageList.add(m);
    }
    

    public ArrayList<Message> filter(Predicate<Message> pred){
    	return (ArrayList<Message>) this.messageList.stream().filter(pred).collect(Collectors.toList());
    }
    
    
    
    public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public TreeSet<Message> getMessageList() {
		return messageList;
	}


	@Override
	public Iterator<Message> iterator() {
		// TODO Auto-generated method stub
		return messageList.iterator();
	}


    
}
