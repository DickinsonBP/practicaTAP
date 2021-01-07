package REDIS;
import OOP.*;
import redis.clients.jedis.Jedis;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.TreeSet;

public class MailStoreRedis extends MailStore{

    Jedis jedis;

    @Override
    public void sendMail(Message m) throws IOException {
        jedis = new Jedis("localhost");
        jedis.set(m.getSender().getUserName(), String.valueOf(m));
        jedis.close();
    }

    @Override
    public TreeSet<Message> getMail() throws FileNotFoundException, ParseException {
        jedis = new Jedis("localhost");
        String message = jedis.get();
        jedis.close();
        return this.messages;
    }
}
