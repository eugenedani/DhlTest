package org.dhltest.framework.log;

import java.io.File;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.status.StatusLogger;

import org.dhltest.framework.CurrentPath;
import org.dhltest.framework.web.driver.WebBrowser;

/**
 * Class is wrapper for getting Log4j Logger. It is wrapped in order to it will be possible to change getting Logger method in one place.
 */
public class AppLogManager {
    static {
        StatusLogger.getLogger().getFallbackListener().setLevel(Level.OFF);
        File file = new File(CurrentPath.getPath() + "/log4j2.xml");
        LoggerContext context = (LoggerContext) LogManager.getContext(false);
        context.setConfigLocation(file.toURI()); //NOSONAR
    }

    private static final ConcurrentHashMap<String, AppMessageFactory> messageFactories = new ConcurrentHashMap<>();

    private AppLogManager() {
    }

    /**
     * Gets Log4j Logger
     *
     * @param name Logger name
     * @return Log4j Logger instance
     */
    public static Logger getLogger(String name) {
        return LogManager.getLogger(name);
    }

    public static Logger getLogger(String name, WebBrowser webBrowser) {
        String browserName = webBrowser.name();
        switch (webBrowser.type()) {
        case Chrome -> messageFactories.computeIfAbsent(browserName, key -> new ChromeMessageFactory());
        case Firefox -> messageFactories.computeIfAbsent(browserName, key -> new FirefoxMessageFactory());
        case Edge -> messageFactories.computeIfAbsent(browserName, key -> new EdgeMessageFactory());
        }

        return LogManager.getLogger(name, messageFactories.get(browserName));
    }
}
