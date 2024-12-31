package lld.logger;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LoggerConfig {
    private Level level;
    private Appender appender;
    private DateTimeFormatter dateTimeFormatter;

    public LoggerConfig(Level level, Appender appender, String timeStampFormat) {
        this.level = level;
        this.appender = appender;
        this.dateTimeFormatter = DateTimeFormatter.ofPattern(timeStampFormat);
    }

    public Level getLevel() {
        return level;
    }

    public Appender getAppender() {
        return appender;
    }

    public String getFormattedTimeStamp(LocalDateTime localDateTime) {
        return localDateTime.format(dateTimeFormatter);
    }
}
