package org.dhltest.framework.web.driver;

import java.util.Optional;

/**
 * Class describes WebDriver properties which are, for example, into config XML file
 */
public final class WebDriverProperties {

    private WebBrowserType browserType;

    private WebDriverType driverType;

    private String remoteDriverUrl = "";

    private Capability[] capabilities;
    private boolean headless;

    private String binaryPath = "";

    /**
     * Class constructor. Sets up default properties values Browser - Firefox
     * WebDriver Type - Local
     * WebDriver Capabilities - empty
     */
    public WebDriverProperties() {
        browserType = WebBrowserType.Firefox;
        driverType = WebDriverType.Local;
        capabilities = new Capability[] {};
    }

    public WebDriverType getDriverType() {
        return driverType;
    }

    public void setDriverType(WebDriverType driverType) {
        this.driverType = driverType;
    }

    public String getRemoteDriverUrl() {
        return remoteDriverUrl;
    }

    public void setRemoteDriverUrl(String remoteDriverUrl) {
        this.remoteDriverUrl = Optional.ofNullable(remoteDriverUrl).orElse("");
    }

    public WebBrowserType getBrowserType() {
        return browserType;
    }

    public void setBrowserType(WebBrowserType webBrowserType) {
        browserType = webBrowserType;
    }

    public Capability[] getCapabilities() {
        return capabilities;
    }

    public void setCapabilities(Capability[] capabilities) {
        this.capabilities = capabilities;
    }

    public boolean isHeadless() {
        return headless;
    }

    public void setHeadless(boolean headless) {
        this.headless = headless;
    }

    public String getBinaryPath() {
        return binaryPath;
    }

    public void setBinaryPath(String binaryPath) {
        this.binaryPath = Optional.ofNullable(binaryPath).orElse("");
    }

    /**
     * Describes a WebDriver capability
     */
    public record Capability(String name, Object value) {
    }
}
