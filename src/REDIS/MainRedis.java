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



        /*factory = new MailStoreMemroyFactory();
        mailStore = factory.createMailStore();
        mailSystem = new MailSystem(mailStore);*/

        User u1 = new User("dickinsonbp","Dickinson Bedoya Perez",2000);
        User u2 = new User("anna.gracia","Anna Gracia Colmenarejo",2000);
        User u3 = new User("ainhoabp","Ainhoa Bedoya Perez",2011);


        Message msg1 = new Message("Nueva oferta de jamon!!!!","Tenemos jamones de oferta para la navidad!!!!!",u3,u1);
        //mailStore.sendMail(msg1);
        Message msg2 = new Message("Hola","Feliz navidad hola hola hola hola!!!",u1,u2);
       // mailStore.sendMail(msg2);
        Message msg3 = new Message("Respuesta","Igualmente!!",u2,u1);
        //mailStore.sendMail(msg3);
        Message msg4 = new Message("Neveras a buen precio!!!!","Tenemos neveras a buen precio estas navidades!!!",u3,u1);
       // mailStore.sendMail(msg4);

        //int count = mailSystem.totalMessages();
        //System.out.println("Memory messages: "+mailSystem.allSystemMessages());

        factory = new MailStoreRedisFactory();
        mailStore = factory.createMailStore();
        mailSystem = new MailSystem(mailStore);

        mailStore.sendMail(msg1);
        mailStore.sendMail(msg2);
        mailStore.sendMail(msg3);
        mailStore.sendMail(msg4);

        TreeSet<Message> list = mailSystem.allSystemMessages();

        System.out.println("Redis messages: "+list);


    }
}
