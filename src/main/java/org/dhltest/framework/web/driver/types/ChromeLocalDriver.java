package org.dhltest.framework.web.driver.types;

import java.io.File;
import java.util.Optional;

import org.dhltest.framework.ActionWasNotDoneException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.chromium.ChromiumDriverLogLevel;

import org.dhltest.framework.CurrentPath;
import org.dhltest.framework.web.driver.browsers.BrowserCapabilities;

import io.github.bonigarcia.wdm.config.DriverManagerType;

public final class ChromeLocalDriver extends AppDriver {
    
    private final AppWebDriverManager webDriverManager;

    public ChromeLocalDriver( BrowserCapabilities options) {
        super(options, "ChromeLocalDriver");
        webDriverManager = new AppWebDriverManager(options.driverProperties(), DriverManagerType.CHROME);
    }

    
    @Override
    public WebDriver create() {
        ChromeDriver webDriver = new ChromeDriver(service(), (ChromeOptions) capabilities());
        logger.info(Optional.ofNullable(webDriver.getCapabilities().getCapability("chrome")).orElse("Chrome WebDriver version number was not found"));
        return webDriver;
    }

    
    private ChromeDriverService service() {
        ChromeDriverService.Builder builder = new ChromeDriverService.Builder();

        if (webDriverManager.setup()) {
            return builder.withLogLevel(ChromiumDriverLogLevel.OFF).usingAnyFreePort().build();
        }

        throw new ActionWasNotDoneException("WebDriver manager did not setup Chrome WebDriver.");
    }
}
