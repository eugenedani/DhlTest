package org.dhltest.framework.web.driver.browsers;

import java.util.Arrays;

import org.dhltest.framework.web.driver.WebDriverProperties;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.remote.AbstractDriverOptions;

public abstract class BrowserCapabilities {
    private final WebDriverProperties driverProperties;

    protected BrowserCapabilities(WebDriverProperties driverProperties) {
        this.driverProperties = driverProperties;
    }

    public abstract MutableCapabilities createdCapabilities();

    public WebDriverProperties driverProperties() {
        return driverProperties;
    }

    protected boolean headless() {
        return driverProperties.isHeadless();
    }

    protected String binaryPath() {
        return driverProperties.getBinaryPath();
    }

    protected MutableCapabilities addCustomCapabilitiesTo(MutableCapabilities browserCapabilities) {
        Arrays.stream(driverProperties.getCapabilities()).forEach(capability -> browserCapabilities.setCapability(capability.name(), capability.value()));
        return browserCapabilities;
    }

    protected void addOptions(AbstractDriverOptions<?> options) {
        options.setUnhandledPromptBehaviour(UnexpectedAlertBehaviour.IGNORE).setPageLoadStrategy(PageLoadStrategy.NORMAL).setEnableDownloads(true);
    }
}
