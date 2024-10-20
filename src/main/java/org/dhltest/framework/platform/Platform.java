package org.dhltest.framework.platform;


import org.dhltest.framework.log.AppLogManager;
import org.dhltest.framework.web.driver.WebBrowser;

public enum Platform {
    LINUX, WINDOWS, MAC;

    public static Platform current(WebBrowser webBrowser) {
        String userAgentInfo = (String) webBrowser.executeScript("return navigator.userAgent;");
        AppLogManager.getLogger(Platform.class.getName(), webBrowser).info("User Agent Info: {}", userAgentInfo);
        if (userAgentInfo.toUpperCase().contains(LINUX.name())) {
            return LINUX;
        } else if (userAgentInfo.toUpperCase().contains(WINDOWS.name())) {
            return WINDOWS;
        } else if (userAgentInfo.toUpperCase().contains("MACINTOSH")) {
            return MAC;
        }

        throw new UnknownError("Unknown or Unsupported Operating System platform: " + userAgentInfo);
    }
}
