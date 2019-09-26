package store.business.util.logger;

import store.business.util.logger.color.Color;
import store.business.util.logger.level.Level;

public class ConsoleLogger implements Logger{

    @Override
    public void log(String message, Level level) {
        switch (level) {
            case INFO:
                System.out.print(Color.BLUE);
                System.out.print(level.toString());
                System.out.print(Color.RESET);
                System.out.println(": " + message);
                break;
            case WARNING:
                System.out.print(Color.MAGENTA);
                System.out.print(level.toString());
                System.out.print(Color.RESET);
                System.out.println(": " + message);
                break;
            case ERROR:
                System.out.print(Color.RED);
                System.out.print(level.toString());
                System.out.print(Color.RESET);
                System.out.println(": " + message);
                break;
        }
    }
}
