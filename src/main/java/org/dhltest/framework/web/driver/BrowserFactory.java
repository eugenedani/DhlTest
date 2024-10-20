package org.dhltest.framework.web.driver;

import org.dhltest.framework.log.AppLogManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;

public final class BrowserFactory {

    private final WebDriverProperties webDriverProperties;

    private BrowserFactory(WebDriverProperties webDriverProperties) {
        this.webDriverProperties = webDriverProperties;
    }

    /**
     * @return {@link WebBrowser} for specific {@link WebDriver) from {@link WebDriverProperties} class
     */

    public static WebBrowser openNewBrowserWith(WebDriverProperties webDriverProperties) {
        return new BrowserFactory(webDriverProperties).initializeWebDriver();
    }

    private WebBrowser initializeWebDriver() {
        WebBrowser webBrowser;
        try {
            webBrowser = new WebBrowser(webDriverProperties);
        } catch (WebDriverException ex) {
            String message = webDriverProperties.getBrowserType().name() + " browser was not started. It will be re-started.  Exception:";
            AppLogManager.getLogger(getClass().getName()).warn(message, ex);
            webBrowser = new WebBrowser(webDriverProperties);
        }

        Runtime.getRuntime().addShutdownHook(new Thread(webBrowser::quit));
        webBrowser.manage().window().maximize();
        return webBrowser;
    }
}
