package org.dhltest.framework.wait;

/**
 * Describes waiting time values in seconds
 */
public enum WaitTime implements Timeout {
    Second1(1),
    Seconds2(2),
    Seconds3(3),
    Seconds4(4),
    Seconds5(5),
    Seconds10(10),
    Seconds30(30),
    Minute1(60);

    private final int seconds;

    WaitTime(int seconds) {
        this.seconds = seconds;
    }

    /**
     * @return seconds value
     */
    @Override
    public int seconds() {
        return seconds;
    }
}
