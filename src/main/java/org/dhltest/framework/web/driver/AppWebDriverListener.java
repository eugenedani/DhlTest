package org.dhltest.framework.web.driver;

import org.apache.logging.log4j.Logger;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.WebDriverListener;

import org.dhltest.framework.log.AppLogManager;

public class AppWebDriverListener implements WebDriverListener {
    
    private final Logger logger = AppLogManager.getLogger(getClass().getName());
    
    private final String webBrowserName;

    public AppWebDriverListener( String browserName) {
        webBrowserName = browserName;
    }

    @Override
    public void afterGet(WebDriver driver, String url) {
        logInfo("Go to URL '" + url + "'");
    }

    @Override
    public void beforeQuit(WebDriver driver) {
        logInfo("Browser will be closed");
    }

    @Override
    public void afterQuit(WebDriver driver) {
        logInfo("Browser was closed");
    }

    @Override
    public void afterMaximize(WebDriver.Window window) {
        logInfo("Browser window was maximized");
    }

    private void logInfo( String message) {
        logger.info("{}: {}", webBrowserName, message);
    }
}
