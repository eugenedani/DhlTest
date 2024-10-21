package org.dhltest.models.pages.sections;

import java.util.List;

import org.dhltest.framework.web.driver.WebBrowser;
import org.dhltest.framework.web.pages.WebPageSection;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Service row of Services Table of Transit Time Result section
 */
public class ServiceRow extends WebPageSection {
    @FindBy(css = "div[style*='block'] div.c-calculator--product-component-wrapper>div")
    private WebElement topElement;
    private final int index;

    protected ServiceRow(WebBrowser webBrowser, int index) {
        super(webBrowser);
        this.index = index + 2;
    }

    public String serviceName() {
        List<WebElement> elements = topElement.findElements(By.cssSelector("div.c-leadtime--product-box:nth-child(1) div.c-leadtime--product-box-specs:nth-child(" + index + ") span"));
        return elements.isEmpty() ? "" : elements.get(0).getText().trim();
    }
}
