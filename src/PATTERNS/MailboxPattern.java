package PATTERNS;

import java.util.*;

import OOP.*;

public class MailboxPattern extends Mailbox implements Iterable<Message>{
	
	private TreeSet<Message> spam;
	private TreeSet<Message> tooLong;
	private List<Observer> observers;
	
	public MailboxPattern(User user, MailStore mailStore) {
		super(user, mailStore);
		spam = new TreeSet<Message>(new AddComparator());
		tooLong = new TreeSet<Message>(new AddComparator());
		observers = new ArrayList<Observer>();
	}

	public TreeSet<Message> getSpam() {
		return spam;
	}

	public void setSpam(TreeSet<Message> spam) {
		this.spam = spam;
	}

	public List<Observer> getObservers() {
		return observers;
	}

	public void setObservers(List<Observer> observers) {
		this.observers = observers;
	}

	public void addSpam(ArrayList<Message> spam) {
		this.spam.addAll(spam);
		for(Message m : spam) {
			this.getMessageList().remove(m);
		}
	}
	
	public void attach(Observer observer){
        this.observers.add(observer);
    }

    public void notifyAllObservers(MailboxPattern mbx){
        for (Observer observer : observers) {
            observer.update(mbx);
        }
    }
	
    public void setMailboxPattern() {
    	notifyAllObservers(this);
    }

	public void addtooLong(ArrayList<Message> tooLong) {
		this.tooLong.addAll(tooLong);
	}
	public TreeSet<Message> getTooLong() {
		return tooLong;
	}
	
	
	public ArrayList<User> showSpamUsers(){
		ArrayList<User> spamUser = new ArrayList<User>();
		
		for(Message m : this.spam) {
			spamUser.add(m.getSender());
		}
		return spamUser;
	}
}
