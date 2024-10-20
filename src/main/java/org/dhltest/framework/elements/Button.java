package org.dhltest.framework.elements;

import java.util.function.Supplier;

import org.dhltest.framework.web.pages.Page;
import org.dhltest.framework.web.pages.WebPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class Button {
    private final WebElement button;

    public Button(WebElement button) {
        this.button = button;
    }

    public boolean isEnabled() {
        String value = button.getAttribute("readonly");
        return button.isEnabled() && (value == null || !value.trim().equalsIgnoreCase("true"));
    }

    public <T extends Page> T click(Supplier<T> returnAction) {
        button.click();
        return returnAction.get();
    }

    public void click() {
        button.click();
    }
}
