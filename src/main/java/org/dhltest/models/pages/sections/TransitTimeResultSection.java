package org.dhltest.models.pages.sections;

import org.dhltest.framework.wait.PageWaitTime;
import org.dhltest.framework.web.driver.WebBrowser;
import org.dhltest.framework.web.pages.DynamicWebPageSection;
import org.dhltest.framework.web.pages.load.UseForVerify;
import org.dhltest.framework.web.pages.load.Verify;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Verify(status = false, waitTime = PageWaitTime.HEAVY_PANEL)
public class TransitTimeResultSection extends DynamicWebPageSection {
    @UseForVerify
    @FindBy(id = "leadtime-datepicker")
    private WebElement datePickerTextbox;

    /**
     * Checks if section is loaded.
     * If section is not loaded throw {@link org.dhltest.framework.web.pages.PageWasNotLoadedException} Exception
     *
     * @param webBrowser current WebBrowser
     */
    public TransitTimeResultSection(WebBrowser webBrowser) {
        this(webBrowser, PageWaitTime.DEFAULT_VALUE);
        checkIfLoaded();
    }

    /**
     * Checks if section is loaded.
     * If section is not loaded sets isLoaded = false;
     *
     * @param webBrowser   current WebBrowser
     * @param pageWaitTime page loading waiting time
     */
    public TransitTimeResultSection(WebBrowser webBrowser, PageWaitTime pageWaitTime) {
        super(webBrowser, pageWaitTime);
    }

    /**
     * @param index row service index
     * @return row Services table
     */
    public ServiceRow serviceRow(int index) {
        return new ServiceRow(webBrowser, index);
    }
}
