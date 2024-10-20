package org.dhltest.framework;

import java.util.ArrayList;
import java.util.List;

import org.dhltest.framework.web.driver.WebDriverProperties;

/**
 * Allows running junit tests in parallel
 * Creates array of parameters for tests
 */
public class TestSettings {
    private final List<Object[]> settings = new ArrayList<>();
    private int parallelThreads;

    private TestSettings() {
        getSettingsForTests();
    }

    private static class LazyHolder {
        private static final TestSettings INSTANCE = new TestSettings();
    }

    /**
     * Factory method. Creates instance of this class
     *
     * @return instance of this class
     */
    public static TestSettings getInstance() {
        return LazyHolder.INSTANCE;
    }

    /**
     * Returns list of tested parameters
     *
     * @return list of tested parameters
     */
    public List<Object[]> getSettings() {
        parallelThreads = ConfigurationData.getInstance().getParallelThreads();
        return settings;
    }

    /**
     * Returns how many parallel threads can be run during testing
     *
     * @return amount of threads
     */
    int getParallelThreads() {
        return parallelThreads;
    }

    /**
     * Sets programmatically how many parallel threads can be run during test
     * It should be used after {@link #getSettings()} method in order to it apply needed value
     * Original value will be return back at the beginning of next test
     */
    public void setCustomParallelThreads(int threadsNumber) {
        parallelThreads = threadsNumber;
    }

    private void getSettingsForTests() {
        ConfigurationData configurationData = ConfigurationData.getInstance();
        for (WebDriverProperties property : configurationData.getWebDriverProperties()) {
            settings.add(new Object[] { property, property.getBrowserType().name() });
        }
        parallelThreads = settings.size();
    }
}
