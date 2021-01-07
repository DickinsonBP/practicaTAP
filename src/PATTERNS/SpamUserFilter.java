package PATTERNS;

import java.util.*;

import OOP.Message;

public class SpamUserFilter extends Observer{

	@Override
	public void update(MailboxPattern mbx) {
		// TODO Auto-generated method stub
		ArrayList<Message> spam = mbx.filter(p -> p.getSender().getUserName().contains("spam"));
		mbx.addSpam(spam);
	}

}
