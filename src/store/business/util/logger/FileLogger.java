package store.business.util.logger;

import store.business.util.logger.level.Level;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.nio.file.StandardOpenOption.*;

public class FileLogger implements Logger {
    private final Path path;

    public FileLogger(final String pathAsString) {
        this.path = Paths.get(pathAsString).toAbsolutePath();
    }

    @Override
    public void log(String message, Level level) {
        try {
            Files.write(this.path, (level.toString() + ": " + message + "\n").getBytes(), APPEND, CREATE);
        } catch (IOException e) {
            throw new RuntimeException("Cannot write log message to file [" + this.path + "]", e);
        }
    }
}
