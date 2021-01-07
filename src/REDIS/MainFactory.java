package REDIS;

import OOP.MailSystem;

public class MainFactory {
    public static void main(String[] args) {
        AbstractFactory factory = new MailStore3Factory();
        AbstractMailStore mailStore = factory.create();

        MailSystem mailSystem = mailStore.createNewMailSystem();

    }
}
