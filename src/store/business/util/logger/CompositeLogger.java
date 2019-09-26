package store.business.util.logger;

import store.business.util.logger.level.Level;

public class CompositeLogger implements Logger {
    private final Logger devLog;
    private final Logger fileLog;

    @Override
    public void log(String message, Level level) {
        this.devLog.log(message, level);
        this.fileLog.log(message, level);
    }

    public CompositeLogger(final Logger devLog, final Logger fileLog) {
        this.devLog = devLog;
        this.fileLog = fileLog;
    }
}
