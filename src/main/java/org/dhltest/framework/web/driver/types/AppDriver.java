package org.dhltest.framework.web.driver.types;

import java.net.URL;

import org.apache.logging.log4j.Logger;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;

import org.dhltest.framework.log.AppLogManager;
import org.dhltest.framework.web.driver.browsers.BrowserCapabilities;

public abstract class AppDriver {
    
    private final MutableCapabilities capabilities;
    
    protected final Logger logger;

    AppDriver( BrowserCapabilities capabilities,  String loggerName) {
        this.capabilities = capabilities.createdCapabilities();
        logger = AppLogManager.getLogger(loggerName);
    }

    
    public abstract WebDriver create();

    
    protected URL hubUrl( String hubUrl) {
        logger.info("Start Remote WebDriver ({}) on hub {}", capabilities.getBrowserName(), hubUrl);

        try {
            return new URL(hubUrl);
        } catch (java.net.MalformedURLException e) {
            throw new RuntimeException("Remote WebDriver Hub URL has some error: " + e.getMessage());
        }
    }

    
    protected RemoteWebDriver remoteWebDriver( URL remoteAddress,  MutableCapabilities capabilities) {
        RemoteWebDriver remoteWebDriver = new RemoteWebDriver(remoteAddress, capabilities, false);
        remoteWebDriver.setFileDetector(new LocalFileDetector());
        return remoteWebDriver;
    }

    
    protected MutableCapabilities capabilities() {
        return capabilities;
    }
}