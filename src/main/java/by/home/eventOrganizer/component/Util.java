package by.home.eventOrganizer.component;

/**
 * The type Util.
 */

public class Util {

    private static LocalizedMessageSource localizedMessageSource;

    /**
     * Instantiates a new Util.
     *
     * @param localizedMessageSource the localized message source
     */
    public Util(LocalizedMessageSource localizedMessageSource) {
        this.localizedMessageSource = localizedMessageSource;

    }

    /**
     * Validate.
     *
     * @param expression   the expression
     * @param errorMessage the error message
     */
    public static void validate(boolean expression, String errorMessage) {
        if (expression) {
            throw new RuntimeException(localizedMessageSource.getMessage(errorMessage, new Object[]{}));
        }
    }
}
