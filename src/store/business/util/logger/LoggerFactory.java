package store.business.util.logger;

import store.business.util.logger.level.Level;

public class LoggerFactory {
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
