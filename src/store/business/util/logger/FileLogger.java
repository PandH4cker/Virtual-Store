package store.business.util.logger;

import store.business.util.logger.level.Level;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.nio.file.StandardOpenOption.*;

/**
 * <h1>The file logger</h1>
 * <p>
 *     Logs messages into a file.
 *     It implements the {@code public interface Logger} interface.
 * </p>
 * <img src="../../../../uml/FileLoggerDiagram.jpg" />
 *
 * @author Raphael Dray
 * @version 1.0.0
 * @since 1.0.0
 * @see Logger
 * @see Path
 */
public class FileLogger implements Logger {
    private final Path path;

    /**
     * Initialize the path of the file where the logs are going to
     * @param pathAsString The path as string
     * @see Paths
     */
    public FileLogger(final String pathAsString) {
        this.path = Paths.get(pathAsString).toAbsolutePath();
    }

    /**
     * Logs messages into a file
     * @param message {@inheritDoc}
     * @param level {@inheritDoc}
     * @see Override
     * @see Level
     * @see Files
     * @see java.nio.file.StandardOpenOption
     * @see Byte
     * @exception RuntimeException Cannot write log message to file
     * @see IOException
     * @see RuntimeException
     */
    @Override
    public void log(String message, Level level) {
        try {
            Files.write(this.path, (level.toString() + ": " + message + "\n").getBytes(), APPEND, CREATE);
        } catch (IOException e) {
            throw new RuntimeException("Cannot write log message to file [" + this.path + "]", e);
        }
    }
}