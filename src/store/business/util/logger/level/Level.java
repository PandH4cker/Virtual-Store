package store.business.util.logger.level;

public enum Level {
    ERROR("Erreur"),
    WARNING("Warning"),
    INFO("Info");

    private String levelName;

    Level(final String levelName) {
        this.levelName = levelName;
    }

    @Override
    public String toString() {
        return this.levelName.toUpperCase();
    }
}
