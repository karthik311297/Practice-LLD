package lld.logger;

import java.time.LocalDateTime;

public class Logger {

    private LoggerConfig loggerConfig;
    private static final Logger logger = new Logger();

    private Logger() {
        // In production read from env or configuration like xml/properties file like we do for log4j.
        loggerConfig = new LoggerConfig(Level.DEBUG, new ConsoleAppender(), "yyyy-MM-dd HH:mm:ss.SSS");
    }

    public static Logger getLogger() {
        return logger;
    }

    private void log(Level level, String message) {
        if (level.ordinal() >= loggerConfig.getLevel().ordinal())
        {
            LogMessage logMessage = new LogMessage(level, message,
                    loggerConfig.getFormattedTimeStamp(LocalDateTime.now()), Thread.currentThread().getId());
            loggerConfig.getAppender().append(logMessage);
        }
    }

    public void debug(String message) {
        log(Level.DEBUG, message);
    }

    public void info(String message) {
        log(Level.INFO, message);
    }

    public void error(String message) {
        log(Level.ERROR, message);
    }

    public void warn(String message) {
        log(Level.WARN, message);
    }
}
