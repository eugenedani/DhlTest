package org.dhltest.framework.elements;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WrapsElement;
import org.openqa.selenium.interactions.Coordinates;
import org.openqa.selenium.interactions.Locatable;

public class NoSuchElement implements WebElement, WrapsElement, TakesScreenshot, Locatable {
    private final String message;

    public NoSuchElement() {
        this("");
    }

    public NoSuchElement(String message) {
        this.message = message;
    }

    @Override
    public void click() {
        throwException();
    }

    @Override
    public void submit() {
        throwException();
    }

    @Override
    public void sendKeys(CharSequence... keysToSend) {
        throwException();
    }

    @Override
    public void clear() {
        throwException();
    }

    @Override
    public String getTagName() {
        throw new NoSuchElementException(message());
    }

    @Override
    public String getAttribute(String name) {
        throw new NoSuchElementException(message());
    }

    @Override
    public boolean isSelected() {
        throw new NoSuchElementException(message());
    }

    @Override
    public boolean isEnabled() {
        throw new NoSuchElementException(message());
    }

    @Override
    public String getText() {
        throw new NoSuchElementException(message());
    }

    @Override
    public List<WebElement> findElements(By by) {
        throw new NoSuchElementException(message());
    }

    @Override
    public WebElement findElement(By by) {
        throw new NoSuchElementException(message());
    }

    @Override
    public boolean isDisplayed() {
        return false;
    }

    @Override
    public Point getLocation() {
        throw new NoSuchElementException(message());
    }

    @Override
    public Dimension getSize() {
        throw new NoSuchElementException(message());
    }

    @Override
    public Rectangle getRect() {
        throw new NoSuchElementException(message());
    }

    @Override
    public String getCssValue(String propertyName) {
        throw new NoSuchElementException(message());
    }

    @Override
    public <X> X getScreenshotAs(OutputType<X> target) {
        throw new NoSuchElementException(message());
    }

    @Override
    public WebElement getWrappedElement() {
        throw new NoSuchElementException(message());
    }

    @Override
    public Coordinates getCoordinates() {
        throw new NoSuchElementException(message());
    }

    private String message() {
        return "Element was not found. " + message;
    }

    private void throwException() {
        throw new NoSuchElementException(message());
    }
}
