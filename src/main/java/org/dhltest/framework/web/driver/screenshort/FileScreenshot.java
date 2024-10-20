package org.dhltest.framework.web.driver.screenshort;

import java.io.File;


import org.openqa.selenium.OutputType;

import org.dhltest.framework.web.driver.WebBrowser;

public final class FileScreenshot extends Screenshot {

    
    private final WebBrowser webBrowser;

    public FileScreenshot( WebBrowser webBrowser) {
        super();
        this.webBrowser = webBrowser;
    }

    
    @Override
    protected File screenshot() {
        return webBrowser.getScreenshotAs(OutputType.FILE);
    }
}
