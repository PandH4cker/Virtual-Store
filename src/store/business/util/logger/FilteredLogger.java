package store.business.util.logger;

import java.util.function.Predicate;

public class FilteredLogger implements Logger {
    private final Logger delegateLogger;
    private final Predicate<String> condition;

    @Override
    public void log(String message) {
        if(this.condition.test(message)) delegateLogger.log(message);
    }

    public FilteredLogger(final Logger delegateLogger, final Predicate<String> condition) {
        this.delegateLogger = delegateLogger;
        this.condition = condition;
    }
}
