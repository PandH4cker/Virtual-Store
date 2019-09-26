package store.business.util.logger;

import store.business.util.logger.level.Level;

import java.util.function.Predicate;

public class FilteredLogger implements Logger {
    private final Logger delegateLogger;
    private final Predicate<Level> condition;

    @Override
    public void log(String message, Level level) {
        if(this.condition.test(level)) delegateLogger.log(message, level);
    }

    public FilteredLogger(final Logger delegateLogger, final Predicate<Level> condition) {
        this.delegateLogger = delegateLogger;
        this.condition = condition;
    }
}
