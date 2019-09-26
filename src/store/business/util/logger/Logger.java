package store.business.util.logger;

import store.business.util.logger.level.Level;

public interface Logger {
    void log(String message, Level level);
}
