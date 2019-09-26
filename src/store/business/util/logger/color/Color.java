package store.business.util.logger.color;

public enum Color {
    RESET("\033[0m"),

    // Regular Colors
    RED("\033[0;31m"),      // RED
    BLUE("\033[0;34m"),     // BLUE
    GREEN("\033[0;32m"),    // GREEN
    YELLOW("\033[0;33m"),   // YELLOW
    MAGENTA("\033[0;35m");  // MAGENTA

    private final String code;

    Color(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return this.code;
    }
}
