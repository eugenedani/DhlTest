package org.dhltest.framework.tests;

import java.util.List;

import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Rule;
import org.junit.runners.Parameterized;

import org.dhltest.framework.ConfigurationData;
import org.dhltest.framework.TestSettings;
import org.dhltest.framework.AppWatcherTestRule;
import org.dhltest.framework.log.AppLogManager;
import org.dhltest.framework.web.driver.WebBrowser;
import org.dhltest.framework.web.driver.WebDriverProperties;
import org.dhltest.framework.web.driver.screenshort.DefectImage;
import org.dhltest.framework.web.driver.screenshort.FileScreenshot;

/**
 * Base test class that contains data for all tests
 */
public class BaseTestCase {
    @Rule
    public final AppWatcherTestRule appWatcherTestRule;
    protected final Logger logger;
    protected String testedUrl;
    protected WebBrowser webBrowser;

    public BaseTestCase(WebDriverProperties webDriverProperties, String name) {
        AppWatcherTestRule appWatcherTestRule = new AppWatcherTestRule(webDriverProperties, ConfigurationData.getInstance().testAttempts());
        ConfigurationData configurationData = ConfigurationData.getInstance();
        testedUrl = configurationData.getTestedUrl();
        this.appWatcherTestRule = appWatcherTestRule;
        webBrowser = appWatcherTestRule.webBrowser();
        logger = AppLogManager.getLogger(getClass().getName(), webBrowser);
    }

    @Parameterized.Parameters(name = "{index} : {1}")
    public static List<Object[]> data() {
        return TestSettings.getInstance().getSettings();
    }

    @Before
    public void checkBrowserBefore() {
        checkBrowser();
    }

    @After
    public void checkBrowserAfter() {
        checkBrowser();
    }

    private void checkBrowser() {
        if (!webBrowser.active()) {
            webBrowser = appWatcherTestRule.webBrowser();
        }
    }

    @AfterClass
    public static void afterClass() {
        ConfigurationData.getInstance().quitBrowsers();
    }
}
