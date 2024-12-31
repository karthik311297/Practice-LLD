package lld.logger;

public class LogMessage {
    private Level level;
    private String message;
    private String timeStamp;
    private long threadId;

    public LogMessage(Level level, String message, String timeStamp, long threadId) {
        this.level = level;
        this.message = message;
        this.timeStamp = timeStamp;
        this.threadId = threadId;
    }

    public Level getLevel() {
        return level;
    }

    public String getMessage() {
        return message;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    @Override
    public String toString() {
        return "[" + level + "]" + " [" + threadId + "] " + " [" + timeStamp + "] " + "[" + message + "]";
    }
}
