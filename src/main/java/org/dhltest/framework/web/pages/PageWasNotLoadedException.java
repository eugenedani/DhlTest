package org.dhltest.framework.web.pages;

import java.io.Serial;

/**
 * Shows web page was not loaded during expected time
 */
public class PageWasNotLoadedException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 7526472295622776147L;

    public PageWasNotLoadedException() {
        super();
    }

    /**
     * Class constructor
     *
     * @param message some explanation text
     */
    public PageWasNotLoadedException( String message) {
        super(message);
    }

    /**
     * Class constructor
     *
     * @param message some explanation text
     * @param cause Throwable instance
     */
    public PageWasNotLoadedException( String message,  Throwable cause) {
        super(message, cause);
    }

    public PageWasNotLoadedException( Throwable cause) {
        super(cause);
    }
}