package org.dhltest.framework.web.pages;

import org.dhltest.framework.web.driver.WebBrowser;

/**
 * Base WebPageSection class that should be extended by any page section class
 * Page section is a part of a Web page which can be described as a class
 */
public class WebPageSection extends Page {

    protected WebPageSection(WebBrowser webBrowser) {
        super(webBrowser);
    }
}
