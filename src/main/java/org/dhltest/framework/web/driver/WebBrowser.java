package org.dhltest.framework.web.driver;

import java.util.Arrays;

import org.apache.logging.log4j.Logger;
import org.dhltest.framework.log.AppLogManager;
import org.dhltest.framework.platform.Platform;
import org.dhltest.framework.util.StringUtils;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.interactions.Actions;

/**
 * Decorator for Selenium WebDriver interface
 */
public class WebBrowser extends DelegatingWebDriver {
    
    private final Actions actions;
    
    private final Logger logManager;
    
    private final WebDriverProperties webDriverProperties;
    
    private final Platform platform;
    
    private String browserVersion = "";

    public WebBrowser( WebDriverProperties webDriverProperties) {
        super(webDriverProperties);
        this.webDriverProperties = webDriverProperties;
        actions = new Actions(webDriver);
        logManager = AppLogManager.getLogger(getClass().getName(), this);
        platform = Platform.current(this);
    }

    @Override
    public void quit() {
        if (decoratedWebDriver == null) {
            return;
        }

        logManager.info("Quit WebDriver");
        try {
            super.quit();
        } catch (WebDriverException ex) {
            String warning = "WebDriver was quit with exception:";
            logManager.warn(warning, ex);
        } finally {
            decoratedWebDriver = null;
            webDriver = null;
        }
    }

    @Override
    public void get(String url) {
        switchTo().defaultContent();
        super.get(url);
    }

    /**
     * @return true if WebDriver is not NULL and can be used otherwise false
     */
    public boolean active() {
        if (decoratedWebDriver == null) {
            return false;
        }

        try {
            webDriver.switchTo().window(webDriver.getWindowHandle());
        } catch (NoSuchWindowException ignore) {
            webDriver.switchTo().window(webDriver.getWindowHandles().stream().findFirst().orElseThrow(() -> new NoSuchWindowException("Cannot find any existed window")));
        } catch (Exception ex) {
            String warning = name() + " WebDriver is not active. Exception:";
            logManager.warn(warning, ex);
            return false;
        }

        return true;
    }

    public boolean is( WebBrowserType webBrowserType) {
        return webDriverProperties.getBrowserType() == webBrowserType;
    }

    public boolean oneOf( WebBrowserType... webBrowserTypes) {
        return Arrays.stream(webBrowserTypes).anyMatch(this::is);
    }

    public boolean headless() {
        return webDriverProperties.isHeadless();
    }

    /**
     * @return instance of Selenium {@link Actions} class
     */
    
    public Actions getActions() {
        return actions;
    }

    
    public String name() {
        return webDriverProperties.getBrowserType().name();
    }

    
    public WebBrowserType type() {
        return webDriverProperties.getBrowserType();
    }

    /**
     * @return version of tested browser or empty string if version was not found
     */
    
    public String version() {
        if (browserVersion.isEmpty()) {
            browserVersion = getCapabilities().getBrowserVersion();
        }

        return browserVersion;
    }

    /**
     * @return major version number, for example for Firefox (47, 51, 55 etc.), or -1 if value cannot be parsed
     */
    public int majorVersion() {
        String majorBrowserVersion = version().split("\\.")[0];
        if (StringUtils.isNumeric(majorBrowserVersion)) {
            return Integer.parseInt(majorBrowserVersion);
        }

        return -1;
    }

    
    public Platform platform() {
        return platform;
    }
}
