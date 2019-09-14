package store.business.util.logger;

public class CompositeLogger implements Logger {
    private final Logger devLog;
    private final Logger fileLog;

    @Override
    public void log(String message) {
        this.devLog.log(message);
        this.fileLog.log(message);
    }

    public CompositeLogger(final Logger devLog, final Logger fileLog) {
        this.devLog = devLog;
        this.fileLog = fileLog;
    }
}
