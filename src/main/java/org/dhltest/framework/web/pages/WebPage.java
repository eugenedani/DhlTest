package org.dhltest.framework.web.pages;

import java.util.List;

import org.dhltest.framework.wait.AppWait;
import org.dhltest.framework.wait.PageWaitTime;
import org.dhltest.framework.wait.WaitTime;
import org.dhltest.framework.web.driver.WebBrowser;
import org.dhltest.framework.web.pages.load.PageLoadedChecker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Base WebPage class that should be extended by any page class
 */
public class WebPage extends Page implements PageLoadedCheck {
    @FindBy(id = "onetrust-accept-btn-handler")
    private WebElement acceptButton;
    @FindBy(id = "dhl-va-widget-iframe")
    private WebElement helpIframe;

    private final AppWait wait = new AppWait();
    private final boolean isLoaded;

    /**
     * Checks if page is loaded.
     * If page is not loaded sets isLoaded field = false;
     */
    protected WebPage(WebBrowser webBrowser, PageWaitTime pageWaitTime) {
        super(webBrowser);
        isLoaded = PageLoadedChecker.assume(this, pageWaitTime);
        if (isLoaded && pageWaitTime == PageWaitTime.DEFAULT_VALUE) {
            acceptCookies();
            closeHelper();
        }
    }

    @Override
    public boolean isLoaded() {
        return isLoaded;
    }

    private void acceptCookies() {
        if (wait.forElement(acceptButton, WaitTime.Seconds3)) {
            acceptButton.click();
            wait.forElementDisappear(acceptButton, WaitTime.Seconds2);
        }
    }

    private void closeHelper() {
        if (!wait.forElement(helpIframe, WaitTime.Second1)){
            return;
        }

        webBrowser.switchTo().frame(helpIframe);
        By selector = By.cssSelector("#root div.content button[color][class]");
        if (wait.waitFor(() -> !webBrowser.findElements(selector).isEmpty(), WaitTime.Seconds10)) {
            WebElement button = webBrowser.findElement(selector);
            button.click();
            wait.forElementDisappear(button, WaitTime.Seconds2);
        }
        webBrowser.switchTo().defaultContent();
    }
}
