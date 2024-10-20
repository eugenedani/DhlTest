package org.dhltest.framework.web.pages.load;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.Logger;
import org.dhltest.framework.log.AppLogManager;
import org.dhltest.framework.wait.AppWait;
import org.dhltest.framework.wait.PageWaitTime;
import org.dhltest.framework.wait.Timeout;
import org.dhltest.framework.wait.WaitTime;
import org.dhltest.framework.wait.WaitTimeout;
import org.dhltest.framework.web.driver.WebBrowser;
import org.dhltest.framework.web.pages.Page;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Creates a builder for checking if a page is loaded or not
 */
public class PageIsLoadedController {
    private static final WaitTime DELAY_SECONDS = WaitTime.Seconds2;

    private final WebBrowser webBrowser;

    private final Page page;

    private final Timeout timeout;

    private final List<By> selectors;
    private Timeout remainedTime;
    private long startTime;
    private boolean waitingWasDone;
    private boolean isLoaded;
    private boolean checkPageStatus;

    public PageIsLoadedController(Page page, PageWaitTime pageWaitTime) {
        this.page = page;
        webBrowser = page.webBrowser();
        timeout = pageWaitTime;
        waitingWasDone = false;
        isLoaded = false;
        checkPageStatus = false;
        selectors = new ArrayList<>();
    }

    /**
     * Sets page element that will be used to check if page is loaded
     *
     * @param selector that should be displayed on Page after loading is complete
     * @return instance of {@link org.dhltest.framework.web.pages.load.PageIsLoadedController}
     */
    public PageIsLoadedController element(By selector) {
        if (!waitingWasDone) {
            selectors.add(selector);
        }
        return this;
    }

    /**
     * Activates checking of document.readyState value
     */
    public void completeStatus() {
        if (!waitingWasDone) {
            checkPageStatus = true;
        }
    }

    /**
     * Performs checking of page loading according class settings
     *
     * @return true if page was loaded otherwise - false
     */
    public boolean waitIsLoaded() {
        if (waitingWasDone) {
            return isLoaded;
        }

        Logger logger = AppLogManager.getLogger(PageLoadedChecker.class.getName(), webBrowser);
        waitingWasDone = true;
        remainedTime = new WaitTimeout(timeout.seconds());
        startTime = System.nanoTime();

        if (checkPageStatus && !isStatusComplete()) {
            logger.warn("Document Ready State was not complete during {} seconds.", timeout.seconds());
            return isLoaded;
        }

        if (!isExpectedElementsDisplayed()) {
            logger.warn("Expected page element was not found on the page during {} seconds.", timeout.seconds());
            return isLoaded;
        }

        isLoaded = true;
        return true;
    }

    /**
     * Checks by JavaScript if document.readyState value is complete
     *
     * @param timeout waiting time
     * @return true if document state is complete otherwise false
     */
    private boolean waitStatusIsComplete(Timeout timeout) {
        if (timeout.seconds() <= 0) {
            timeout = WaitTime.Second1;
        }
        WebDriverWait wait = new WebDriverWait(webBrowser, timeout.duration());
        try {
            return wait.until(driver -> driver != null && ((JavascriptExecutor) driver).executeScript("return document.readyState").toString().equalsIgnoreCase("complete"));
        } catch (TimeoutException ex) {
            return false;
        }
    }

    /**
     * @return remained Time for page loading checking.
     */

    private Timeout getRemainedTime() {
        final float convertValue = 1000000000;
        long elapsedTime = System.nanoTime() - startTime;
        Timeout usedTime = new WaitTimeout(Math.round(elapsedTime / convertValue));
        return subtractedTimeout(usedTime);
    }

    private boolean isStatusComplete() {
        if (!waitStatusIsComplete(remainedTime)) {
            return false;
        }

        remainedTime = summedTimeout(getRemainedTime(), DELAY_SECONDS);
        return true;
    }

    private boolean isExpectedElementsDisplayed() {
        //AppWait wait = new AppWait();
        for (By selector : selectors) {
            Wait<WebDriver> wait = new WebDriverWait(webBrowser, remainedTime.duration());
            try {
                if (wait.until(webBrowser -> !webBrowser.findElements(selector).isEmpty() && webBrowser.findElement(selector).isDisplayed())) {
                    //if (wait.forElement(webBrowser.findElement(selector))) {
                    remainedTime = getRemainedTime();
                }
            } catch (TimeoutException ex){
                return false;
            }
        }

        return true;
    }

    public Timeout subtractedTimeout(Timeout timeout2) {
        int seconds = timeout.seconds();
        seconds -= timeout2.seconds();
        return new WaitTimeout(seconds);
    }

    public Timeout summedTimeout(Timeout timeout1, Timeout timeout2) {
        int seconds = timeout1.seconds() + timeout2.seconds();
        return new WaitTimeout(seconds);
    }
}
