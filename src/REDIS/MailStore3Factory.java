package REDIS;

public class MailStore3Factory extends AbstractFactory{

    @Override
    public AbstractMailStore create() {
        return new MailStore3();
    }
}
