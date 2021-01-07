package REDIS;
import OOP.*;
import redis.clients.jedis.Jedis;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Array;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.TreeSet;

public class MailStoreRedis extends MailStore{

    private static Jedis jedis;

    public MailStoreRedis(){
        getInstance();
    }

    public Jedis getInstance(){
        if(this.jedis == null){
            //iniciar solo si no se ha iniciado antes
            jedis = new Jedis("localhost");
        }
        return jedis;
    }


    @Override
    public void sendMail(Message m) throws IOException {
        jedis.lpush(m.getSender().getUserName(), m.toString());
    }

    @Override
    public TreeSet<Message> getAllMessages() throws FileNotFoundException, ParseException {
        return null;
    }

    @Override
    public TreeSet<Message> getUserMessages(String username) throws FileNotFoundException, ParseException {
        TreeSet<Message> userMessage = new TreeSet<Message>((Comparator<? super Message>) jedis.lrange(username,0,-1));

        //userMessage = (TreeSet<Message>) jedis.lrange(username,0,-1);
        return userMessage;
    }

    @Override
    public TreeSet<User> getAllUsers() throws FileNotFoundException, ParseException {
        ArrayList<String> usernames = new ArrayList<String>(jedis.keys("*"));
        TreeSet<User> allUsers = new TreeSet<User>();
        /*obtener todos los usuarios*/

        for(Message msg : this.getAllMessages()){
            for(String username : usernames){
                if(msg.getReceiver().getUserName().equals(username)){
                    allUsers.add(msg.getReceiver());
                }
                if(msg.getSender().getUserName().equals(username)){
                    allUsers.add(msg.getSender());
                }
            }
        }
        return allUsers;
    }

}
