package org.dhltest.framework.web.driver.browsers;

import org.dhltest.framework.web.driver.WebDriverProperties;
import org.openqa.selenium.firefox.FirefoxDriverLogLevel;
import org.openqa.selenium.firefox.FirefoxOptions;

public final class FirefoxCapabilities extends BrowserCapabilities {
    
    private final FirefoxOptions firefoxOptions;

    public FirefoxCapabilities( WebDriverProperties driverProperties) {
        super(driverProperties);
        firefoxOptions = new FirefoxOptions();
    }

    @Override
    
    public FirefoxOptions createdCapabilities() {
        setOptions();
        return (FirefoxOptions) addCustomCapabilitiesTo(firefoxOptions);
    }

    private void setOptions() {
        addOptions(firefoxOptions);
        firefoxOptions.setLogLevel(FirefoxDriverLogLevel.INFO);

        if (!binaryPath().isEmpty()) {
            firefoxOptions.addArguments("-no-remote");
            //for example "C:\Program Files\Mozilla Firefox Beta\firefox.exe"
            firefoxOptions.setBinary(binaryPath());
        }

        if (headless()) {
            firefoxOptions.addArguments("-headless");
        }
    }
}
