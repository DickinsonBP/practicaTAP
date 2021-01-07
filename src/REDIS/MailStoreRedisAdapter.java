package REDIS;

import OOP.AddComparator;
import OOP.MailStore;
import OOP.Message;
import OOP.User;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.TreeSet;

public class MailStoreRedisAdapter extends MailStore {

    private MailStoreRedis mailstore;

    public MailStoreRedisAdapter(MailStoreRedis mailstore){
        this.mailstore = mailstore;
    }

    @Override
    public void sendMail(Message m) throws IOException {
        mailstore.sendMail(m);
    }

    @Override
    public TreeSet<Message> getAllMessages() throws FileNotFoundException, ParseException {
        return mailstore.getAllMessages();
    }

    @Override
    public TreeSet<Message> getUserMessages(String username) throws FileNotFoundException, ParseException {
        return this.mailstore.getUserMessages(username);
    }

    @Override
    public TreeSet<User> getAllUsers() throws FileNotFoundException, ParseException {
        return this.mailstore.getAllUsers();
    }
}
