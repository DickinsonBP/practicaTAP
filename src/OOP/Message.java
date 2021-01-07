package OOP;

import java.security.NoSuchAlgorithmException;

import javax.crypto.NoSuchPaddingException;

public class Message {
    private String subject, body;
    private User sender, receiver;
    private Tiempo time;

    public Message(String subject, User sender, User receiver) {
        this.subject = subject;
        this.sender = sender;
        this.receiver = receiver;
        time = new Tiempo();
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody(){
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public User getReceiver() {
        return receiver;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }

	public Tiempo getTime() {
		return time;
	}

	public void setTime(Tiempo time) {
		this.time = time;
	}
	
	public int getWords(){
		String [] parts = getBody().split(" ");
		return parts.length;
	}

	@Override
    public String toString() {
        return time+";"+sender+";"+subject+";"+body+";"+receiver+"\n";
    }
}
