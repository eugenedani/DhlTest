package org.dhltest.framework.web.pages;

import org.dhltest.framework.wait.PageWaitTime;
import org.dhltest.framework.web.driver.WebBrowser;
import org.dhltest.framework.web.pages.load.PageLoadedChecker;

/**
 * DynamicWebPageSection class that should be extended by any page dynamic section class
 * Page section is a part of a Web page which can be described as a class
 */
public class DynamicWebPageSection extends Page implements PageLoadedCheck {
    private final boolean isLoaded;

    /**
     * Checks if page is loaded.
     * If page is not loaded sets isLoaded field = false;
     */
    public DynamicWebPageSection(WebBrowser webBrowser, PageWaitTime pageWaitTime) {
        super(webBrowser);
        isLoaded = PageLoadedChecker.assume(this, pageWaitTime);
    }

    @Override
    public boolean isLoaded() {
        return isLoaded;
    }
}
