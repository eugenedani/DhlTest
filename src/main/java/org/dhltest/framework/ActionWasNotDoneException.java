package org.dhltest.framework;

import java.io.Serial;

/**
 * Exception for actions that was not done
 */
public class ActionWasNotDoneException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 7526472295622776147L;

    public ActionWasNotDoneException() {
        super();
    }

    /**
     * Class constructor
     *
     * @param message some explanation text
     */
    public ActionWasNotDoneException(String message) {
        super(message);
    }

    /**
     * Class constructor
     *
     * @param message some explanation text
     * @param cause Throwable instance
     */
    public ActionWasNotDoneException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * @param cause Throwable instance
     */
    public ActionWasNotDoneException(Throwable cause) {
        super(cause);
    }
}
