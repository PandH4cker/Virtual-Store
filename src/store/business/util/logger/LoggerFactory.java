package store.business.util.logger;

public class LoggerFactory {
    public static Logger getLogger(String name) {
        return new ContextualLogger(name,
                                    new CompositeLogger(
                                            new ConsoleLogger(),
                                            new FilteredLogger(
                                                    new FileLogger("files/log.txt"),
                                                    message -> message.contains("simulation"))));
    }
}
