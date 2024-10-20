package org.dhltest.framework.elements;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class DropdownList {
    private final WebElement dropdownList;

    public DropdownList(WebElement dropdownList) {
        this.dropdownList = dropdownList;
    }

    public boolean isEnabled() {
        String value = dropdownList.getAttribute("readonly");
        return dropdownList.isEnabled() && (value == null || !value.trim().equalsIgnoreCase("true"));
    }

    public Select select() {
        return new Select(dropdownList);
    }
}
