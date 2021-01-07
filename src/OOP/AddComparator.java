package OOP;

import java.util.*;

public class AddComparator implements Comparator<Message>{

	@Override
	public int compare(Message o1, Message o2) {
		// TODO Auto-generated method stub
		return (o1.getTime().equals(o2.getTime()));
	}
	

}
