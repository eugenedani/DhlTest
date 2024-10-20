package org.dhltest.framework;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import org.apache.logging.log4j.Logger;

import org.dhltest.framework.log.AppLogManager;
import org.dhltest.framework.web.driver.BrowserFactory;
import org.dhltest.framework.web.driver.WebBrowser;
import org.dhltest.framework.web.driver.WebDriverProperties;

/**
 * Data needed to run Selenium tests Contains information from UITest.config file
 */
public class ConfigurationData {
    private String testedUrl = "https://www.dhl.com/se-en/home/freight/tools/";
    private int parallelThreads = 4;
    private WebDriverProperties[] webDriverPropertiesArray = { new WebDriverProperties() };
    private final Map<String, WebBrowser> webBrowsers = new HashMap<>();
    private TestAttempts testAttempts = TestAttempts.TWO;

    /**
     * Class constructor. Read configuration data from UITest.config file
     */
    private ConfigurationData() {
        String enableTracingSystemProperty = "webdriver.remote.enableTracing";
        System.setProperty(enableTracingSystemProperty, "false");
        readWebDriverPropertiesFromConfigFile();
        Logger logger = AppLogManager.getLogger(ConfigurationData.class.getName());
        logger.info("Current Operating System: {}", System.getProperty("os.name"));
        logger.info("Current Architecture: {}", System.getProperty("os.arch"));
        logger.info("{}: {}", enableTracingSystemProperty, System.getProperty(enableTracingSystemProperty));
        logger.info("Tested URL: {}", testedUrl);
        logger.info("Test attempts: {}", testAttempts);
    }

    private static class LazyHolder {
        private static final ConfigurationData INSTANCE = new ConfigurationData();
    }

    /**
     * Factory method of the class. Creates a new class instance or returns existed one.
     *
     * @return this class instance
     */
    public static ConfigurationData getInstance() {
        return LazyHolder.INSTANCE;
    }

    /**
     * Returns based URL. For example, https://www.google.com/
     *
     * @return based tested URL
     */
    public String getTestedUrl() {
        return testedUrl;
    }

    /**
     * Returns list of tested web browsers properties
     *
     * @return array of tested web browsers properties
     */
    public WebDriverProperties[] getWebDriverProperties() {
        return webDriverPropertiesArray;
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
     * @return amount of how many times failed tests will be run
     */
    public TestAttempts testAttempts() {
        return testAttempts;
    }

    /**
     * Returns expected instance of {@link WebBrowser}
     * If it does not exist when creates it
     *
     * @param webDriverProperties needed Browser properties
     * @return instance of {@link WebBrowser}
     */
    WebBrowser getWebBrowserWith(WebDriverProperties webDriverProperties) {
        Logger logger = AppLogManager.getLogger(getClass().getName());
        String browserName = webDriverProperties.getBrowserType().name();

        WebBrowser webBrowser = webBrowsers.get(browserName);
        if (webBrowser != null) {
            if (webBrowser.active()) {
                return webBrowser;
            }

            logger.warn("{} browser is not active anymore and it will be recreated.", browserName);
            webBrowser.quit();
        }

        logger.info("Open new {} browser.", browserName);
        webBrowser = BrowserFactory.openNewBrowserWith(webDriverProperties);
        logger.info("Add new {} browser to cache.", browserName);
        webBrowsers.put(browserName, webBrowser);
        return webBrowser;
    }

    public void quitBrowsers() {
        webBrowsers.values().stream().filter(Objects::nonNull).forEach(WebBrowser::quit);
        webBrowsers.clear();
    }

    private void readWebDriverPropertiesFromConfigFile() {
        File theFile = new File(CurrentPath.getPath(), "UITest.config");
        if (!theFile.exists()) {
            return;
        }

        ConfigurationFile configurationFile;
        try {
            configurationFile = ConfigurationFile.deserializeFromXmlFile(theFile.getAbsolutePath());
        } catch (FileNotFoundException e) {
            AppLogManager.getLogger(getClass().getName()).error("UITest.config file was not found.", e);
            return;
        }

        webDriverPropertiesArray = configurationFile.getDrivers();
        if (webDriverPropertiesArray.length == 0) {
            AppLogManager.getLogger(getClass().getName()).error("Browser(s) settings were not found in UITest.config file");
            return;
        }

        setValuesFromConfigFile(configurationFile);
    }

    private void setValuesFromConfigFile(ConfigurationFile configurationFile) {
        testedUrl = configurationFile.getTestedUrl();
        parallelThreads = configurationFile.getParallelThreads();
        testAttempts = TestAttempts.testAttempts(configurationFile.getTestAttempts());
    }
}
