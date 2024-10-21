package org.dhltest.framework.elements;

import org.openqa.selenium.WebElement;

public class Textbox {
    private final WebElement textbox;

    public Textbox(WebElement textbox) {
        this.textbox = textbox;
    }

    public boolean isEnabled() {
        String value = textbox.getAttribute("readonly");
        return textbox.isEnabled() && (value == null || !value.trim().equalsIgnoreCase("true"));
    }

    public void fill(String value) {
        if (isReadyToFill(value)) {
            textbox.sendKeys(value);
        }
    }

    public String getValue() {
        return textbox.getAttribute("value").trim();
    }

    private boolean isReadyToFill(String value) {
        String currentValue = getValue();
        if (currentValue.equals(value)) {
            return false;
        }

        if (!currentValue.isEmpty()) {
            textbox.clear();
        }

        return !value.trim().isEmpty();
    }
}
