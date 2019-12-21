package store.business.util.logger;

import store.business.util.logger.level.Level;

/**
 * <h1>The logger factory</h1>
 * <p>
 *     Used to get an instance of a logger
 * </p>
 * <img src="../../../../uml/LoggerFactoryDiagram.jpg" />
 *
 * @author Raphael Dray
 * @version 1.0.0
 * @since 1.0.0
 * @see Logger
 * @see ContextualLogger
 * @see CompositeLogger
 * @see ConsoleLogger
 * @see FilteredLogger
 * @see FileLogger
 * @see java.util.function.Predicate<Level>
 */
public class LoggerFactory {

    /**
     * Get an instance of a logger
     * @param name The name of the class
     * @return Logger An instance of a logger
     */
    public static Logger getLogger(String name) {
        return new ContextualLogger(name,
                                    new CompositeLogger(
                                            new ConsoleLogger(),
                                            new FilteredLogger(
                                                    new FileLogger("files/log.txt"),
                                                    level -> level == Level.ERROR
                                                             || level == Level.WARNING)));
    }
}