package org.dhltest.framework.web.driver.browsers;

import java.util.HashMap;

import org.dhltest.framework.web.driver.WebDriverProperties;
import org.dhltest.framework.web.driver.windows.HeadlessWindowDimension;
import org.openqa.selenium.chrome.ChromeOptions;

public final class ChromeCapabilities extends BrowserCapabilities {

    private final ChromeOptions chromeOptions;

    public ChromeCapabilities(WebDriverProperties driverProperties) {
        super(driverProperties);
        chromeOptions = new ChromeOptions();
    }

    @Override
    public ChromeOptions createdCapabilities() {
        setOptions();
        return (ChromeOptions) addCustomCapabilitiesTo(chromeOptions);
    }

    private void setOptions() {
        addArguments();
        addOptions();
        addPreferences();
    }

    private void addArguments() {
        chromeOptions.addArguments("--no-default-browser-check", "--enable-automation", "--start-maximized", "--disable-password-generation", "--disable-search-engine-choice-screen");

        if (headless()) {
            chromeOptions.addArguments("--headless=new", new HeadlessWindowDimension().chromeWindowSize());
        }
    }

    private void addOptions() {
        addOptions(chromeOptions);

        if (!binaryPath().isEmpty()) {
            //For example "C:\Program Files (x86)\Google\Chrome Beta\Application\chrome.exe"
            chromeOptions.setBinary(binaryPath());
        }
    }

    private void addPreferences() {
        HashMap<String, Object> chromePreferences = new HashMap<>();
        chromePreferences.put("credentials_enable_service", false);
        chromePreferences.put("profile.password_manager_enabled", false);
        chromeOptions.setExperimentalOption("prefs", chromePreferences);
    }
}
