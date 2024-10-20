package org.dhltest.framework.web.pages;

import org.apache.logging.log4j.Logger;
import org.dhltest.framework.log.AppLogManager;
import org.dhltest.framework.web.driver.WebBrowser;
import org.openqa.selenium.support.PageFactory;

/**
 * Has main functionality for web pages
 */
public class Page {
    protected final WebBrowser webBrowser;
    protected final Logger logger;

    /**
     * Class constructor. Initialize Elements
     */
    protected Page(WebBrowser webBrowser) {
        this.webBrowser = webBrowser;
        logger = AppLogManager.getLogger(getClass().getName(), webBrowser);
        refresh();
    }

    public WebBrowser webBrowser() {
        return webBrowser;
    }

    /**
     * Initialize page Elements
     */
    private void refresh() {
        PageFactory.initElements(webBrowser, this);
    }
}
