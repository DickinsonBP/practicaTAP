package PART4;

import OOP.*;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.ParseException;

public class MainReflective {
    private static MailSystem mailSystem;
    private static User dickinson = new User("dickinsonbp","Dickinson Bedoya Perez",2000);
    private static User anna = new User("annagracia","Anna Gracia",2000);

    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, IOException, ParseException {
        String name = "OOP.MailStoreMemory";
        Boolean login = true;
        config(name,login);
    }


    private static void config(String name, Boolean login)
            throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException, ParseException {

        if(login){
            Class aClass = Class.forName(name);//mail store
            Object newObject = aClass.newInstance();
            MailStore mailStore = (MailStore) newObject;
            mailSystem = new MailSystem(mailStore);

            mailSystem.newUser(dickinson);
            mailSystem.newUser(anna);

            Message msg = new Message("Hola",dickinson,anna);
            msg.setBody("Es un saludo");
            mailStore.sendMail(msg);

            Mailbox mbx = mailSystem.getMailBox().get(dickinson.getUserName());
            mbx.updateMail();
            System.out.println(mbx.getMessageList());
        }
    }
}

