package org.dhltest.framework.wait;

import java.time.Duration;

public interface Timeout {
    int seconds();

    default long milliseconds() {
        return duration().toMillis();
    }

    default Duration duration() {
        return Duration.ofSeconds(seconds());
    }
}
