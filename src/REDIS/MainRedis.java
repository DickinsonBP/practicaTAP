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
        AbstractMailStoreFactory factory;
        MailStore mailStore;
        MailSystem mailSystem;

        factory = new MemoryMailStoreFactory();
        mailSystem = new MailSystem(factory.createMailStore());
        System.out.println("Memory numberMessagesmail:\t" + mailSystem.numberMessages());

        factory = new FileMailStoreFactory();
        mailSystem = new MailSystem(factory.createMailStore());
        System.out.println("File numberMessagesmail:\t" + mailSystem.numberMessages());

        factory = new RedisMailStoreFactory();
        mailSystem = new MailSystem(factory.createMailStore());
        System.out.println("Redis numberMessages:\t\t" + mailSystem.numberMessages());

    }
}
