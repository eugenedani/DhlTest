package org.dhltest.framework.wait;

import java.time.Duration;

public class WaitTimeout implements Timeout {
    private final Duration duration;

    public WaitTimeout(Duration duration) {
        this.duration = duration;
    }

    public WaitTimeout(int seconds) {
        this(Duration.ofSeconds(Math.max(seconds, 0)));
    }

    @Override
    public int seconds() {
        return Math.toIntExact(duration.toSeconds());
    }

    @Override
    public Duration duration() {
        return duration;
    }
}
