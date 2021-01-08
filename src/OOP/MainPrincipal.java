package OOP;

import java.io.IOException;
import java.text.ParseException;
import java.util.TreeSet;
import java.util.function.Predicate;

public class MainPrincipal {
    public static void main(String[] args) throws IOException, ParseException {
        MailStore mailStore;
        mailStore = new MailStoreMemory();
        MailSystem mailSystem = new MailSystem(mailStore);

        User u1 = new User("dickinsonbp","Dickinson",2000);
		User u2 = new User("ainhoa","Ainhoa Bedoya Perez",2011);
		User u3 = new User("paulap","Paula Perez Ospina",1976);

        Mailbox mbx1 = mailSystem.newUser(u1);
        Mailbox mbx2 = mailSystem.newUser(u2);
        Mailbox mbx3 = mailSystem.newUser(u3);

        Message msg1 = new Message("hola","Esto es un mensaje de prueba",u1,u2);
        mailStore.sendMail(msg1);
        Message msg2 = new Message("trabajo","Ya he acabado mi parte del trabajo, te lo envio",u2,u3);
        mailStore.sendMail(msg2);
        Message msg3 = new Message("lista de la compra","pan, agua, bebida, azucar, café, aguacates, tomate",u3,u1);
        mailStore.sendMail(msg3);

        mbx1.updateMail();
        mbx2.updateMail();
        mbx3.updateMail();

        Predicate<Message> pred;

        //mostrar mensajes de la mbx1
        System.out.println("Mail box 1: "+mbx1.getMessageList());
        //filtrar mbx1 por sneder
        pred = p -> p.getSender().getUserName().equals(u1.getUserName());
        System.out.println("Mensajes mbx1 filtrados por sender: "+mbx1.filter(pred));
        //mostrar todos los mensajes del sistema
        System.out.println("Todos los mensajes: "+mailSystem.getMailStore().getAllMessages());
        //flitrar a nivel global por asunto 1 palabra
        pred = p -> p.getSubject().length() == 1;
        TreeSet<Message> list = mailSystem.filterMessages(pred);
        System.out.println("Filtrar todos los mensajes por un el asunto 1 palabra: "+list);
        //filtrar sujeto por palabra
        pred = p -> p.getSubject().contains("trabajo");
        System.out.println("Filtrar mensajes por sujeto = trabajo: "+ mailSystem.filterMessages(pred));
        //filtrar mensajes que sean despues del 2000
        pred = p -> p.getSender().getDateBirth() > 2000;
        System.out.println("Menores del 2000: "+mailSystem.filterMessages(pred));
        //filtrar mensajes que sean antes del 2000
        pred = p -> p.getSender().getDateBirth() < 2000;
        System.out.println("Mayores del 2000: "+mailSystem.filterMessages(pred));
        //contar las palabras de los mensjes de un usuario
        int numWords = mailSystem.countWords(u1.getUserName());
        System.out.println("Total palabras de un usuario: "+numWords);
        //mostrar número de mensages
        System.out.println("Hay "+mailStore.getAllMessages().size()+" mensajes en total");
        //mostrar media de mensages por usuario
        System.out.println("Media de mensajes por usuario "+mailSystem.averageUserMessage());

    }

}
