package org.dhltest.framework.wait;

import java.time.Duration;
import java.util.Collection;
import java.util.List;
import java.util.function.BooleanSupplier;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;

/**
 * This class is for waiting until a condition is true or not null.
 * Each method has the maximum amount of time to wait for a condition
 */
public class AppWait {
    private static final WaitTime DEFAULT_TIMEOUT = WaitTime.Seconds5;

    /**
     * Default sleeps thread for 500 milliseconds
     */
    public static void threadSleep() {
        threadSleep(Duration.ofMillis(500));
    }

    /**
     * Sleeps thread for specific amount of time
     *
     * @param waitTime The sleep timeouts
     */
    public static void threadSleep(Timeout waitTime) {
        threadSleep(waitTime.duration());
    }


    /**
     * Waits for condition
     *
     * @param condition the argument to pass
     * @param timeout   the timeout when it stops waiting
     * @param waitTime  interval between running condition
     * @return true if condition becomes true otherwise false
     */
    public boolean waitFor(BooleanSupplier condition, Timeout timeout, Timeout waitTime) {

        try {
            if (condition.getAsBoolean()) {
                return true;
            } else if (timeout.seconds() <= 0) {
                return false;
            }
        } catch (StaleElementReferenceException | NoSuchElementException | NoSuchFrameException | TimeoutException ex) {
            //do nothing because it will be handled by entity of PolarionFluentWait class
        }

        Collection<Class<? extends WebDriverException>> collection = List.of(StaleElementReferenceException.class, NoSuchElementException.class, NoSuchFrameException.class, TimeoutException.class);

        try {
            return new FluentWait<>(condition)
                    .pollingEvery(waitTime.duration())
                    .withTimeout(timeout.duration())
                    .ignoreAll(collection)
                    .until(BooleanSupplier::getAsBoolean);
        } catch (TimeoutException ignore) {
            return false;
        }
    }

    /**
     * Waits for condition
     *
     * @param condition the argument to pass
     * @param timeout   the timeout when it stops waiting
     * @return true if condition becomes true otherwise false
     */
    public boolean waitFor(BooleanSupplier condition, Timeout timeout) {
        return waitFor(condition, timeout, new WaitTimeout(Duration.ofMillis(500)));
    }

    /**
     * Waits default timeout for condition
     *
     * @param condition the argument to pass
     * @return true if condition becomes true otherwise false
     */
    public boolean waitFor(BooleanSupplier condition) {
        return waitFor(condition, DEFAULT_TIMEOUT);
    }

    /**
     * Waits default timeout for element is present on the DOM of a page
     *
     * @param element expected element
     * @return true if element exists on page otherwise false
     */
    public boolean forElement(WebElement element) {
        return forElement(element, DEFAULT_TIMEOUT);
    }

    /**
     * Waits for element is present on the DOM of a page
     *
     * @param element        expected element
     * @param secondsTimeout the timeout in seconds when it stops waiting
     * @return true if element exists on page otherwise false
     */
    public boolean forElement(WebElement element, Timeout secondsTimeout) {
        return waitFor(element::isDisplayed, secondsTimeout);
    }

    /**
     * Waits for element is disappeared from a page
     *
     * @param element expected Element
     * @return true if element disappeared otherwise false
     */
    public boolean forElementDisappear(WebElement element) {
        return forElementDisappear(element, DEFAULT_TIMEOUT);
    }

    /**
     * Waits for element is disappeared from a page
     *
     * @param element        expected Element
     * @param secondsTimeout the timeout in seconds when it stops waiting
     * @return true if element disappeared otherwise false
     */
    public boolean forElementDisappear(WebElement element, Timeout secondsTimeout) {
        return waitFor(() -> !element.isDisplayed(), secondsTimeout);
    }

    /**
     * Sleeps thread for specific amount of time
     *
     * @param millis The timeout in milliseconds
     */
    private static void threadSleep(Duration millis) {
        try {
            Thread.sleep(millis.toMillis());
        } catch (InterruptedException ignored) {
            Thread.currentThread().interrupt();
        }
    }
}
