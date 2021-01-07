package REDIS;

import OOP.MailStore;
import OOP.MailStoreMemory;

public class MailStoreMemroyFactory implements AbstractMailStoreFactory{

    @Override
    public MailStore createMailStore() {
        return new MailStoreMemory();
    }
}
