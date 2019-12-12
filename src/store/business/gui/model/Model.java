package store.business.gui.model;


import java.util.List;

public interface Model<X extends Throwable> {
    default boolean ensureNotNull(Object field, String errorMsg, List<String> errors) {
        boolean result = true;
        if(field == null) {
            errors.add(errorMsg);
            result = false;
        }
        return result;
    }

    default boolean hasContent(String s) {
        return s != null && s.trim().length() > 0;
    }

    void validate() throws X;
}
