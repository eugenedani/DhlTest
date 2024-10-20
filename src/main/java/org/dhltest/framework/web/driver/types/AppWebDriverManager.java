package org.dhltest.framework.web.driver.types;

import java.util.List;



import org.dhltest.framework.log.AppLogManager;
import org.dhltest.framework.web.driver.WebDriverProperties;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.DriverManagerType;

public class AppWebDriverManager {
    
    private final WebDriverProperties driverProperties;
    
    private final WebDriverManager manager;

    public AppWebDriverManager( WebDriverProperties driverProperties,  DriverManagerType type) {
        this.driverProperties = driverProperties;
        this.manager = WebDriverManager.getInstance(type);
    }

    public boolean setup() {
        applySettings();

        try {
            manager.setup();
            return true;
        } catch (Exception ex) {
            String errorMessage = manager.getDriverManagerType() + " WebDriver was not found with WebDriverManager";
            AppLogManager.getLogger(getClass().getName()).warn(errorMessage, ex);
            return false;
        }
    }

    private void applySettings() {
        if (!driverProperties.getBinaryPath().isEmpty()) {
            manager.browserVersionDetectionCommand(commandFor(driverProperties.getBinaryPath()));
        }

        if (manager.getDriverManagerType() == DriverManagerType.FIREFOX) {
            List<String> drivers = manager.getDriverVersions();
            String firefoxVersion = drivers.get(drivers.size() - 1).split("-")[0];
            manager.driverVersion(firefoxVersion);
        }
    }

    
    private String commandFor( String browserPath) {
        String osName = System.getProperty("os.name").toLowerCase();
        if (osName.startsWith("windows")) {
            return "cmd.exe /C wmic datafile where name=\"" + browserPath.replace("/", "\\").replace("\\", "\\\\") + "\" get Version /value";
        } else if (osName.startsWith("mac")) {
            DriverManagerType driverType = manager.getDriverManagerType();
            switch (driverType) {
            case FIREFOX:
                return browserPath + " -v";
            case CHROME:
                return browserPath + " --version";
            case EDGE:
                return browserPath + " -version";
            default:
                throw new UnsupportedOperationException("Unsupported Mac Web Browser " + driverType.getBrowserName());
            }
        }
        throw new UnsupportedOperationException("Unsupported Operation System: " + osName);
    }
}
