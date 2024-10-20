package org.dhltest.framework;

public enum TestAttempts {
    ONE(1), TWO(2), THREE(3);

    private final int amount;

    TestAttempts(int amount) {
        this.amount = amount;
    }

    public int value() {
        return amount;
    }

    public static TestAttempts testAttempts(int amount) {
        for (TestAttempts testAttempts : TestAttempts.values()) {
            if (testAttempts.value() == amount) {
                return testAttempts;
            }
        }

        throw new RuntimeException("Unexpected amount of attempts. Your value: " + amount + " but it can be 1, 2 or 3 only");
    }
}
