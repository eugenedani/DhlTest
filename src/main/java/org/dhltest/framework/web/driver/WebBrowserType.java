package org.dhltest.framework.web.driver;

import org.dhltest.framework.web.driver.browsers.BrowserCapabilities;
import org.dhltest.framework.web.driver.browsers.ChromeCapabilities;
import org.dhltest.framework.web.driver.browsers.EdgeCapabilities;
import org.dhltest.framework.web.driver.browsers.FirefoxCapabilities;
import org.dhltest.framework.web.driver.types.ChromeLocalDriver;
import org.dhltest.framework.web.driver.types.EdgeLocalDriver;
import org.dhltest.framework.web.driver.types.FirefoxLocalDriver;
import org.dhltest.framework.web.driver.types.AppDriver;

/**
 * Describes browser types that can be used for creating Selenium WebDriver
 */
public enum WebBrowserType {
    Firefox {
        @Override
        public AppDriver localDriver(BrowserCapabilities capabilities) {
            return new FirefoxLocalDriver(capabilities);
        }

        @Override
        public BrowserCapabilities browserCapabilities(WebDriverProperties driverProperties) {
            return new FirefoxCapabilities(driverProperties);
        }
    },
    Chrome {
        @Override
        public AppDriver localDriver(BrowserCapabilities capabilities) {
            return new ChromeLocalDriver(capabilities);
        }

        @Override
        public BrowserCapabilities browserCapabilities(WebDriverProperties driverProperties) {
            return new ChromeCapabilities(driverProperties);
        }
    },
    Edge {
        @Override
        public AppDriver localDriver(BrowserCapabilities capabilities) {
            return new EdgeLocalDriver(capabilities);
        }

        @Override
        public BrowserCapabilities browserCapabilities(WebDriverProperties driverProperties) {
            return new EdgeCapabilities(driverProperties);
        }
    };

    public abstract BrowserCapabilities browserCapabilities(WebDriverProperties driverProperties);

    public abstract AppDriver localDriver(BrowserCapabilities capabilities);
}
