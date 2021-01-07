package REDIS;

import OOP.*;
import redis.clients.jedis.Jedis;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;

/*iniciar docker desde la terminal con:
    docker run -p 6379:6379 redis --protected-mode no
*/
public class MainRedis {
    public static void main(String[] args) throws IOException, ParseException {
        MailStore mailStore = new MailStoreRedis();
        User dickinson = new User("dickinsonbp","Dickinson Bedoya Perez",2000);
        User anna = new User("annagracia","Anna Gracia",2000);

        Message msg1 = new Message("Saludo",dickinson,anna);
        msg1.setBody("Hola Anna!!!!!");

        mailStore.sendMail(msg1);
        Message msg2 = new Message("Despedida",anna,dickinson);
        msg2.setBody("Adios Dickinson!!!!");

        mailStore.sendMail(msg2);
        Message msg3 = new Message("Triste",dickinson,anna);
        msg3.setBody("Pq me rechazas :(");

        mailStore.sendMail(msg3);

        System.out.println(mailStore.getMail());
    }
}
