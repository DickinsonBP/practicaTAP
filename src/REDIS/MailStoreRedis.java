package REDIS;
import OOP.*;
import com.sun.source.tree.Tree;
import redis.clients.jedis.Jedis;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Array;
import java.text.ParseException;
import java.util.*;

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

    //prar jedis
    public void stop(){
        this.jedis.close();
    }


    @Override
    public void sendMail(Message m) throws IOException {
        this.jedis.lpush(m.getSender().getUserName(),m.toString());
    }

    @Override
    public TreeSet<Message> getAllMessages() throws FileNotFoundException, ParseException {
        List<Message> list = new ArrayList<Message>();
        for(String username : this.getUserNames()){
            TreeSet<Message> userMessage = this.getUserMessages(username);
            list.addAll(userMessage);
        }
        TreeSet<Message> messageList = new TreeSet<Message>(new AddComparator());
        messageList.addAll(list);
        return messageList;
    }

    @Override
    public TreeSet<Message> getUserMessages(String username) throws FileNotFoundException, ParseException {
        List<String> list = jedis.lrange(username,0,-1);
        User sender = null,receiver=null;
        TreeSet<Message> userMessage = new TreeSet<Message>(new AddComparator());

        for(String message : list){
            //fecha;username-name-datebirth;subject;body;username-name-datebirth
            String [] parts = message.split(";");
            String [] senderParts = parts[1].split("-");
            String [] receiverParts = parts[4].split("-");
            sender.setUserName(senderParts[0]);
            sender.setName(senderParts[1]);
            sender.setDateBirth(Integer.parseInt(senderParts[2]));

            receiver.setUserName(receiverParts[0]);
            receiver.setName(receiverParts[1]);
            receiver.setDateBirth(Integer.parseInt(receiverParts[2]));

            Message msg = new Message(parts[2],parts[3],sender,receiver);

            //añadir en el set
            userMessage.add(msg);
        }
        return userMessage;
    }

    @Override
    public TreeSet<User> getAllUsers() throws FileNotFoundException, ParseException {
        /*Set<String> usernames = (jedis.keys("*"));
        TreeSet<User> allUsers = new TreeSet<User>();

        for(Message msg : this.getAllMessages()){
            for(String username : usernames){
                if(msg.getReceiver().getUserName().equals(username)){
                    allUsers.add(msg.getReceiver());
                }
                if(msg.getSender().getUserName().equals(username)){
                    allUsers.add(msg.getSender());
                }
            }
        }*/
        return null;
    }

    private Set<String> getUserNames(){
        return jedis.keys("*");
    }
}
