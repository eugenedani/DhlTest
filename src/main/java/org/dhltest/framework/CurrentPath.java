package org.dhltest.framework;

import java.io.File;
import java.nio.file.Paths;

/**
 * Class for getting path to root project folder in order to have possibility reading files
 */
public class CurrentPath {
    //private static final String SELENIUM_WORK_DIR = "selenium.work.dir";
    private String workDirPath = null;

    private CurrentPath() {
        setCurrentPath();
    }

    /**
     * Gets absolute path to out root folder of project
     *
     * @return absolute path
     */
    public static String getPath() {
        return LazyHolder.INSTANCE.workDirPath;
    }

    private void setCurrentPath() {
        String userDir = System.getProperty("user.dir");
        File file = Paths.get(userDir, "src", "test", "workdir").toFile();
        workDirPath = file.getAbsolutePath();
    }

    private static class LazyHolder {
        private static final CurrentPath INSTANCE = new CurrentPath();
    }
}
