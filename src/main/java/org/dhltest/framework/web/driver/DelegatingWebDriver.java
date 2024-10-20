package org.dhltest.framework.web.driver;

import java.io.IOException;
import java.nio.file.Path;
import java.time.Duration;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.dhltest.framework.ActionWasNotDoneException;
import org.dhltest.framework.wait.Timeout;
import org.dhltest.framework.wait.WaitTime;
import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.HasCapabilities;
import org.openqa.selenium.HasDownloads;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Pdf;
import org.openqa.selenium.PrintsPage;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WrapsDriver;
import org.openqa.selenium.interactions.Interactive;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.print.PrintOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.events.EventFiringDecorator;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.virtualauthenticator.HasVirtualAuthenticator;
import org.openqa.selenium.virtualauthenticator.VirtualAuthenticator;
import org.openqa.selenium.virtualauthenticator.VirtualAuthenticatorOptions;

/**
 * Make WebBrowser class backwards compatible, so it can be used in the code where expecting a WebDriver interface as parameter.
 */
abstract class DelegatingWebDriver implements WebDriver,
        JavascriptExecutor,
        HasCapabilities,
        HasDownloads,
        HasVirtualAuthenticator,
        Interactive,
        PrintsPage,
        TakesScreenshot,
        WrapsDriver {
    private final boolean isRemoteWebDriver;
    /**
     * This is original WebDriver. It should be used in cases when {@link #decoratedWebDriver} doesn't work properly.
     */
    protected WebDriver webDriver;
    /**
     * This is Decorated WebDriver to have access to some WebDriver events. It should be used by default.
     * If something doesn't work try to use {@link #webDriver} instead of it.
     */
    protected WebDriver decoratedWebDriver;

    protected DelegatingWebDriver(WebDriverProperties webDriverProperties) {
        this.webDriver = webDriverProperties.getDriverType().appDriver(webDriverProperties).create();
        this.decoratedWebDriver = new EventFiringDecorator<>(new AppWebDriverListener(webDriverProperties.getBrowserType().name())).decorate(webDriver);
        this.isRemoteWebDriver = webDriver.getClass().equals(RemoteWebDriver.class);
    }

    @Override
    public void get(String url) {
        decoratedWebDriver.get(url);
    }

    @Override
    public String getCurrentUrl() {
        switchTo().defaultContent();
        return decoratedWebDriver.getCurrentUrl();
    }

    @Override
    public String getTitle() {
        return decoratedWebDriver.getTitle();
    }

    @Override
    public List<WebElement> findElements(By by) {
        return webDriver.findElements(by);
    }

    /**
     * Override method in order to find element without exception and wait default time until element is found on page
     *
     * @param by element selector
     * @return Selenium WebElement
     */
    @Override

    public WebElement findElement(By by) {
        return findElement(by, WaitTime.Seconds2);
    }

    /**
     * Finds element without exception and wait specific time until element is found on a page
     *
     * @param by element selector
     * @param timeout the timeout when it stops waiting for element
     * @return Selenium WebElement
     */

    public WebElement findElement(By by, Timeout timeout) {
        int secondsTimeout = timeout.seconds();
        if (secondsTimeout <= 0) {
            secondsTimeout = 1;
        }

        WebDriverWait webDriverWait = new WebDriverWait(webDriver, WaitTime.Second1.duration(), Duration.ofMillis(500));
        return webDriverWait.withTimeout(Duration.ofSeconds(secondsTimeout)).until(ExpectedConditions.presenceOfElementLocated(by));
    }

    @Override
    public String getPageSource() {
        return decoratedWebDriver.getPageSource();
    }

    @Override
    public void close() {
        decoratedWebDriver.close();
    }

    @Override
    public void quit() {
        decoratedWebDriver.quit();
    }

    @Override
    public Set<String> getWindowHandles() {
        return decoratedWebDriver.getWindowHandles();
    }

    @Override
    public String getWindowHandle() {
        return decoratedWebDriver.getWindowHandle();
    }

    @Override
    public TargetLocator switchTo() {
        return decoratedWebDriver.switchTo();
    }

    @Override
    public Navigation navigate() {
        return decoratedWebDriver.navigate();
    }

    @Override
    public Options manage() {
        return decoratedWebDriver.manage();
    }

    @Override
    public Object executeScript(String s, Object... objects) {
        return ((JavascriptExecutor) webDriver).executeScript(s, objects);
    }

    @Override
    public Object executeAsyncScript(String s, Object... objects) {
        return ((JavascriptExecutor) webDriver).executeAsyncScript(s, objects);
    }

    @Override
    public <X> X getScreenshotAs(OutputType<X> outputType) {
        try {
            return ((TakesScreenshot) webDriver).getScreenshotAs(outputType);
        } catch (WebDriverException ex) {
            throw new WebDriverException(ex.getCause());
        }
    }

    @Override
    public Pdf print(PrintOptions printOptions) {
        return isRemoteWebDriver ? ((RemoteWebDriver) webDriver).print(printOptions) : ((PrintsPage) webDriver).print(printOptions);
    }

    @Override
    public VirtualAuthenticator addVirtualAuthenticator(VirtualAuthenticatorOptions options) {
        return isRemoteWebDriver ? ((RemoteWebDriver) webDriver).addVirtualAuthenticator(options) : ((HasVirtualAuthenticator) webDriver).addVirtualAuthenticator(options);
    }

    @Override
    public void removeVirtualAuthenticator(VirtualAuthenticator authenticator) {
        if (isRemoteWebDriver) {
            ((RemoteWebDriver) webDriver).removeVirtualAuthenticator(authenticator);
        } else {
            ((HasVirtualAuthenticator) webDriver).removeVirtualAuthenticator(authenticator);
        }
    }

    @Override
    public Capabilities getCapabilities() {
        return isRemoteWebDriver ? ((RemoteWebDriver) webDriver).getCapabilities() : ((HasCapabilities) webDriver).getCapabilities();
    }

    @Override
    public WebDriver getWrappedDriver() {
        return webDriver instanceof WrapsDriver ? ((WrapsDriver) webDriver).getWrappedDriver() : webDriver;
    }

    @Override
    public void perform(Collection<Sequence> collection) {
        if (isRemoteWebDriver) {
            ((RemoteWebDriver) webDriver).perform(collection);
        } else {
            ((Interactive) webDriver).perform(collection);
        }
    }

    @Override
    public void resetInputState() {
        if (isRemoteWebDriver) {
            ((RemoteWebDriver) webDriver).resetInputState();
        } else {
            ((Interactive) webDriver).resetInputState();
        }
    }

    @Override
    public List<String> getDownloadableFiles() {
        return ((HasDownloads) webDriver).getDownloadableFiles();
    }

    @Override
    public void downloadFile(String fileName, Path targetLocation) {
        try {
            ((HasDownloads) webDriver).downloadFile(fileName, targetLocation);
        } catch (IOException ex) {
            throw new ActionWasNotDoneException(fileName + " file cannot be saved to '" + targetLocation.toAbsolutePath() + "' path", ex);
        }
    }

    @Override
    public void deleteDownloadableFiles() {
        ((HasDownloads) webDriver).deleteDownloadableFiles();
    }
}
