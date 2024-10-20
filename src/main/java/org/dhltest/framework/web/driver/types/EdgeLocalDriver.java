package org.dhltest.framework.web.driver.types;

import java.util.Optional;

import org.dhltest.framework.ActionWasNotDoneException;
import org.dhltest.framework.web.driver.browsers.BrowserCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

import io.github.bonigarcia.wdm.config.DriverManagerType;

public final class EdgeLocalDriver extends AppDriver {

    private final AppWebDriverManager webDriverManager;

    public EdgeLocalDriver(BrowserCapabilities options) {
        super(options, "EdgeLocalDriver");
        webDriverManager = new AppWebDriverManager(options.driverProperties(), DriverManagerType.EDGE);
    }

    @Override
    public WebDriver create() {
        if (webDriverManager.setup()) {
            EdgeDriver webDriver = new EdgeDriver((EdgeOptions) capabilities());
            logger.info(Optional.ofNullable(webDriver.getCapabilities().getCapability("msedge")).orElse("Edge WebDriver version number was not found"));
            return webDriver;
        }

        throw new ActionWasNotDoneException("WebDriver manager did not setup Edge WebDriver.");
    }
}
