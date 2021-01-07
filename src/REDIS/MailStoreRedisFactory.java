package REDIS;

import OOP.MailStore;

public class MailStoreRedisFactory implements AbstractMailStoreFactory{
    @Override
    public MailStore createMailStore() {
        return new MailStoreRedisAdapter(new MailStoreRedis());
    }
}
