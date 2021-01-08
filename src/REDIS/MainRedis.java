package REDIS;

import OOP.*;
import redis.clients.jedis.Jedis;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.TreeSet;

/*iniciar docker desde la terminal con:
    docker run -p 6379:6379 redis --protected-mode no
*/
public class MainRedis {
    public static void main(String[] args) throws IOException, ParseException {
        AbstractMailStoreFactory factory;
        MailStore mailStore;
        MailSystem mailSystem;

        factory = new MailStoreRedisFactory();
        mailStore = factory.createMailStore();
        mailSystem = new MailSystem(mailStore);

        User u1 = new User("dickinsonbp","Dickinson Bedoya Perez",2000);
        User u2 = new User("anna.gracia","Anna Gracia Colmenarejo",2000);
        User u3 = new User("ainhoabp","Ainhoa Bedoya Perez",2011);

        Mailbox mbx1 = mailSystem.newUser(u1);
        Mailbox mbx2 = mailSystem.newUser(u2);
        Mailbox mbx3 = mailSystem.newUser(u3);

        Message msg1 = new Message("Hola","Feliz navidad hola hola hola hola!!!",u1,u2);
        Message msg2 = new Message("Respuesta","Igualmente!!",u2,u1);
        Message msg3 = new Message("Neveras a buen precio!!!!","Tenemos neveras a buen precio estas navidades!!!",u3,u1);

        mailStore.sendMail(msg1);
        mailStore.sendMail(msg2);
        mailStore.sendMail(msg3);

        mbx1.updateMail();
        mbx2.updateMail();
        mbx3.updateMail();

        TreeSet<Message> list = mailSystem.allSystemMessages();

        System.out.println("Redis messages: "+list);


    }
}
