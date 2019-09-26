package store.business.util.logger;

import store.business.util.logger.color.Color;
import store.business.util.logger.level.Level;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ContextualLogger implements Logger {
    private final Logger delegateLogger;
    private final String callerClass;

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss.SSS");

    @Override
    public void log(String message, Level level) {
        this.delegateLogger.log(String.format("%s%s %s%s%s: %s",
                            Color.GREEN,
                            DATE_FORMAT.format(new Date()),
                            Color.YELLOW,
                            this.callerClass,
                            Color.RESET,
                            message), level);
    }

    public ContextualLogger(final String callerClass, final Logger delegateLogger) {
        this.callerClass = callerClass;
        this.delegateLogger = delegateLogger;
    }
}
