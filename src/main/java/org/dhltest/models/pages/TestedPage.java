package org.dhltest.models.pages;

import org.apache.commons.lang3.StringUtils;
import org.dhltest.framework.ConfigurationData;
import org.dhltest.framework.wait.PageWaitTime;
import org.dhltest.framework.web.driver.WebBrowser;
import org.dhltest.framework.web.pages.WebPage;
import org.dhltest.framework.web.pages.load.Verify;
import org.dhltest.models.pages.sections.ChooseRouteSection;
import org.openqa.selenium.support.FindBy;

@Verify(waitTime = PageWaitTime.LIGHT_PAGE)
@FindBy(id = "title-d4c4a28dba")
public class TestedPage extends WebPage {

    /**
     * Checks if page is loaded.
     * If page is not loaded throw {@link org.dhltest.framework.web.pages.PageWasNotLoadedException} Exception
     *
     * @param webBrowser current WebBrowser
     */
    public TestedPage(WebBrowser webBrowser) {
        this(webBrowser, PageWaitTime.DEFAULT_VALUE);
        checkIfLoaded();
    }

    /**
     * Checks if page is loaded.
     * If page is not loaded sets isLoaded field = false;
     *
     * @param webBrowser   current WebBrowser
     * @param pageWaitTime page loading waiting time
     */
    public TestedPage(WebBrowser webBrowser, PageWaitTime pageWaitTime) {
        super(webBrowser, pageWaitTime);
    }

    public static TestedPage load(WebBrowser webBrowser) {
        TestedPage testedPage = new TestedPage(webBrowser, PageWaitTime.MINIMUM);
        if (testedPage.isLoaded()) {
            return testedPage;
        }
        String pageUrl = StringUtils.strip(ConfigurationData.getInstance().getTestedUrl(),"/") + "/european-road-freight-transit-time-calculator.html";
        webBrowser.get(pageUrl);
        return new TestedPage(webBrowser);
    }

    public ChooseRouteSection chooseRouteSection(){
        return new ChooseRouteSection(webBrowser);
    }
}
