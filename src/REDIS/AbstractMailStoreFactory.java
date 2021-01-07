package REDIS;

import OOP.MailStore;

public interface AbstractMailStoreFactory {
    public MailStore createMailStore();
}
