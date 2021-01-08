package REDIS;
import OOP.*;
import com.sun.source.tree.Tree;
import redis.clients.jedis.Jedis;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Array;
import java.text.ParseException;
import java.util.*;

public class MailStoreRedis{

    private static Jedis jedis;

    public MailStoreRedis(){
        getInstance();
    }

    public Jedis getInstance(){
        if(this.jedis == null){
            //iniciar solo si no se ha iniciado antes
            jedis = new Jedis("localhost");
            Set<String> keys = jedis.keys("*");
            for(String i : keys){
                jedis.del(i);
            }
        }
        return jedis;
    }

    //prar jedis
    public void stop(){
        this.jedis.close();
    }



    public void sendMail(Message m) throws IOException {
        //this.jedis.del(m.getSender().getUserName());
        this.jedis.lpush(m.getSender().getUserName(),m.toString());
    }


    public TreeSet<Message> getAllMessages() throws FileNotFoundException, ParseException {
        TreeSet<Message> messageList = new TreeSet<Message>(new AddComparator());
        for(String username : this.getUserNames()){
            TreeSet<Message> userMessage = this.getUserMessages(username);
            messageList.addAll(userMessage);
        }
        return messageList;
    }


    public TreeSet<Message> getUserMessages(String username) throws FileNotFoundException, ParseException {
        List<String> list = jedis.lrange(username,0,-1);
        User sender,receiver;
        TreeSet<Message> userMessage = new TreeSet<Message>(new AddComparator());

        for(String message : list){
            //fecha;username-name-datebirth;subject;body;username-name-datebirth
            String [] parts = message.split(";");
            String [] senderParts = parts[1].split("-");
            String [] receiverParts = parts[4].split("-");
            sender = new User(senderParts[0],senderParts[1],Integer.parseInt(senderParts[2]));

            receiver = new User(receiverParts[0],receiverParts[1],Integer.parseInt(quitarSalto(receiverParts[2])));

            Message msg = new Message(parts[2],parts[3],sender,receiver);

            //añadir en el set
            userMessage.add(msg);
        }
        return userMessage;
    }


    private String quitarSalto(String msg){
        return msg.replaceAll("\n", "");
    }
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
        Set<String> names = jedis.keys("*");
        return names;
    }
}
