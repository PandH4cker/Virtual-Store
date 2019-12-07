package store.business.util.logger;

import store.business.util.logger.level.Level;

/**
 * <h1>The logger interface</h1>
 * <p>
 *     Define a log function to be implemented.
 * </p>
 * <img src="../../../../uml/Logger.png" />
 * @author Raphael Dray
 * @version 1.0.0
 * @since 1.0.0
 * @see Level
 */
public interface Logger {
    /**
     * Logs a message
     * @param message The message of the log
     * @param level The level of the message
     * @see Level
     */
    void log(String message, Level level);
}