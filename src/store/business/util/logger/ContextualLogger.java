package store.business.util.logger;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ContextualLogger implements Logger {
    private final Logger delegateLogger;
    private final String callerClass;

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss.SSS");

    @Override
    public void log(String message) {
        this.delegateLogger.log(String.format("%s %s %s",
                            DATE_FORMAT.format(new Date()),
                            this.callerClass,
                            message));
    }

    public ContextualLogger(final String callerClass, final Logger delegateLogger) {
        this.callerClass = callerClass;
        this.delegateLogger = delegateLogger;
    }
}
