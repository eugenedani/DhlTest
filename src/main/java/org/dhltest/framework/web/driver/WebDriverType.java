package org.dhltest.framework.web.driver;

import org.dhltest.framework.web.driver.browsers.BrowserCapabilities;
import org.dhltest.framework.web.driver.types.AppDriver;
import org.dhltest.framework.web.driver.types.RemoteDriver;

/**
 * Describes types of WebDriver Local - Selenium WebDriver run on local machine
 * Remote - Selenium use Remote WebDriver to run test on a remote machine
 */
public enum WebDriverType {
    Local {
        @Override
        public AppDriver appDriver(WebDriverProperties driverProperties) {
            return driverProperties.getBrowserType().localDriver(browserCapabilities(driverProperties));
        }
    },
    Remote {
        @Override
        public AppDriver appDriver(WebDriverProperties driverProperties) {
            return new RemoteDriver(browserCapabilities(driverProperties), driverProperties.getRemoteDriverUrl());
        }

    };

    public abstract AppDriver appDriver(WebDriverProperties driverProperties);

    protected BrowserCapabilities browserCapabilities(WebDriverProperties driverProperties) {
        return driverProperties.getBrowserType().browserCapabilities(driverProperties);
    }
}
