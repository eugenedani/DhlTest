package org.dhltest.framework.wait;

/**
 * Describes waiting time values for web page loading waiting
 */
public enum PageWaitTime implements Timeout {
    //@formatter:off
    DEFAULT_VALUE(-1),
    MINIMUM(2),
    LIGHT_PANEL(4),
    PANEL(6),
    HEAVY_PANEL(10),
    LIGHT_PAGE(20),
    PAGE(30),
    HEAVY_PAGE(40),
    MAXIMUM(60);

    private final int seconds;

    PageWaitTime(int seconds) {
        this.seconds = seconds;
    }

    /**
     * Returns seconds value
     *
     * @return seconds
     */
    @Override
    public int seconds() {
        return seconds;
    }
}
