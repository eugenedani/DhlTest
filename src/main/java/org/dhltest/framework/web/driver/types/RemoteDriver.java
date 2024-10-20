package org.dhltest.framework.web.driver.types;

import java.util.Map;
import java.util.Optional;

import org.apache.logging.log4j.Logger;
import org.dhltest.framework.log.AppLogManager;
import org.dhltest.framework.web.driver.browsers.BrowserCapabilities;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

public final class RemoteDriver extends AppDriver {
    private final String hubUrl;

    public RemoteDriver( BrowserCapabilities capabilities,  String customHubUrl) {
        super(capabilities, "RemoteDriver");
        hubUrl = url(customHubUrl);
    }

    
    @Override
    public WebDriver create() {
        RemoteWebDriver webDriver = remoteWebDriver(hubUrl(hubUrl), capabilities());
        printDriverVersion(webDriver.getCapabilities());
        return webDriver;
    }

    
    private String url( String customUrl) {
        return customUrl.isEmpty() ? "http://localhost:4444/wd/hub" : customUrl;
    }

    private void printDriverVersion( Capabilities capabilities) {
        Logger logger = AppLogManager.getLogger(getClass().getName());
        String browserName = capabilities.getBrowserName();

        String webDriverVersion;
        switch (browserName.toLowerCase()) {
        case "firefox":
            webDriverVersion = (Optional.ofNullable(capabilities.getCapability("moz:geckodriverVersion")).orElse("was not found")).toString();
            browserName = "Firefox";
            break;
        case "microsoftedge", "msedge-headless-shell", "msedge":
            webDriverVersion = version(capabilities, "msedge");
            browserName = "Edge";
            break;
        case "chrome":
            webDriverVersion = version(capabilities, "chrome");
            browserName = "Chrome";
            break;
        default:
            webDriverVersion = "unsupported Web Browser";
        }

        logger.info("{} WebDriver version number is {}", browserName, webDriverVersion);
    }

    
    private String version( Capabilities capabilities,  String capabilityName) {
        Object map = capabilities.getCapability(capabilityName);
        if (map == null) {
            return "was not found";
        }
        return Optional.ofNullable(((Map<String, String>) map).get(capabilityName + "driverVersion")).orElse("was not found");
    }
}