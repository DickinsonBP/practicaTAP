package REDIS;

import OOP.*;

public class MailStore3 extends AbstractMailStore{
    MailStore ms = new MailStoreMemory();
    @Override
    public MailSystem createNewMailSystem() {
        return new MailSystem(ms);
    }
}
