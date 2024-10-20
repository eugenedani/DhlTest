package org.dhltest.framework.web.driver.types;

import java.util.Optional;

import org.dhltest.framework.ActionWasNotDoneException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import org.dhltest.framework.CurrentPath;
import org.dhltest.framework.web.driver.browsers.BrowserCapabilities;

import io.github.bonigarcia.wdm.config.DriverManagerType;

public final class FirefoxLocalDriver extends AppDriver {
    
    private final AppWebDriverManager webDriverManager;

    public FirefoxLocalDriver( BrowserCapabilities options) {
        super(options, "FirefoxLocalDriver");
        webDriverManager = new AppWebDriverManager(options.driverProperties(), DriverManagerType.FIREFOX);
    }

    
    @Override
    public WebDriver create() {
        if (webDriverManager.setup()) {
            FirefoxDriver webDriver = new FirefoxDriver((FirefoxOptions) capabilities());
            logger.info("Firefox WebDriver version number {}", Optional.ofNullable(webDriver.getCapabilities().getCapability("moz:geckodriverVersion")).orElse("was not found"));
            return webDriver;
        }

        throw new ActionWasNotDoneException("WebDriver manager did not setup Firefox Gecko WebDriver.");
    }
}
