package lld.logger;

public class ConsoleAppender implements Appender{

    @Override
    public void append(LogMessage message) {
        System.out.println(message);
    }
}
