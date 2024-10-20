package org.dhltest.framework;

import java.io.Serial;

/**
 * Exception for not allowed actions
 */
public class ForbiddenActionException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 7526472295622776147L;

    public ForbiddenActionException(String message) {
        super(message);
    }

    public ForbiddenActionException(String message, Throwable cause) {
        super(message, cause);
    }
}
