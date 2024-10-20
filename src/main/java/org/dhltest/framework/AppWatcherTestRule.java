package org.dhltest.framework;

import org.apache.logging.log4j.Logger;
import org.dhltest.framework.log.AppLogManager;
import org.dhltest.framework.web.driver.WebBrowser;
import org.dhltest.framework.web.driver.WebDriverProperties;
import org.dhltest.framework.web.driver.screenshort.DefectImage;
import org.dhltest.framework.web.driver.screenshort.FileScreenshot;
import org.junit.runner.Description;

/**
 * Extends junit TestWatcher in order to initialize WebDriver at proper time and react on test events into one place
 */
public class AppWatcherTestRule extends RetryTestRule {
    private final Logger logger = AppLogManager.getLogger(AppWatcherTestRule.class.getName());
    protected final WebDriverProperties webDriverProperties;
    protected WebBrowser webBrowser;

    protected AppWatcherTestRule(WebDriverProperties webDriverProperties) {
        this(webDriverProperties, TestAttempts.ONE);
    }

    public AppWatcherTestRule(WebDriverProperties webDriverProperties, TestAttempts testAttempts) {
        super(testAttempts);
        this.webDriverProperties = webDriverProperties;
    }

    /**
     * Activate WebDriver according to properties from config file
     *
     * @return WebBrowser
     */
    public WebBrowser webBrowser() {
        webBrowser = ConfigurationData.getInstance().getWebBrowserWith(webDriverProperties);
        String info = "Browser: " + webBrowser.name() + " " + webBrowser.version() + ", WebDriver type: " + webDriverProperties.getDriverType().name() + ", Headless: " + webBrowser.headless();
        logger.info(info);
        return webBrowser;
    }

    /**
     * Override method in order to every failed event can make screenshot
     *
     * @param e Throwable
     * @param description junit test description
     */
    @Override
    protected void failed(Throwable e, Description description) {
        StringBuilder errorMessage = new StringBuilder(fullTestName(description)).append(" test failed.");
        if (webBrowser != null && webBrowser.active()) {
            webBrowser.switchTo().defaultContent();
            DefectImage defectImage = new DefectImage(webBrowser, shortTestName(description));
            new FileScreenshot(webBrowser).saveTo(defectImage);
            errorMessage.append(" Browser: ").append(webBrowser.name());
        } else {
            errorMessage.append(" Screenshot was not made because WebDriver was not active.");
        }
        logger.error(errorMessage);
    }

    @Override
    protected void starting() {
        // Do nothing because it does not need.
    }

    /**
     * Quits WebDriver after test is finished
     *
     * @param description junit test description
     */
    @Override
    protected void finished(Description description) {
        // Do nothing because it does not need.
    }
}
