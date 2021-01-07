package PATTERNS;

import java.util.ArrayList;

import OOP.Message;

public class TooLongFilter extends Observer{

	@Override
	public void update(MailboxPattern mbx) {
		// TODO Auto-generated method stub
		
		ArrayList<Message> tooLong = mbx.filter(p -> p.getBody().length() > 20);
		mbx.addtooLong(tooLong);
	}

}
