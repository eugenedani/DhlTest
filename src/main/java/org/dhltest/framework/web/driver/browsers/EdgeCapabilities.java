package org.dhltest.framework.web.driver.browsers;


import org.openqa.selenium.edge.EdgeOptions;

import org.dhltest.framework.web.driver.WebDriverProperties;
import org.dhltest.framework.web.driver.windows.HeadlessWindowDimension;

public final class EdgeCapabilities extends BrowserCapabilities {
    
    private final EdgeOptions options;

    public EdgeCapabilities( WebDriverProperties driverProperties) {
        super(driverProperties);
        options = new EdgeOptions();
    }
    
    @Override
    public EdgeOptions createdCapabilities() {
        addArguments();
        addOptions(options);
        return (EdgeOptions) addCustomCapabilitiesTo(options);
    }

    private void addArguments() {
        options.addArguments("--no-default-browser-check", "--enable-automation", "--disable-component-extensions-with-background-pages", "--disable-extensions", "--start-maximized");
        if (headless()) {
            options.addArguments("--headless", new HeadlessWindowDimension().chromeWindowSize());
        }
    }
}
