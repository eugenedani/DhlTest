package org.dhltest.framework.web.driver.screenshort;

import java.io.File;
import java.nio.file.Paths;
import java.time.LocalDateTime;



import org.dhltest.framework.log.AppLogManager;
import org.dhltest.framework.util.DateUtils;
import org.dhltest.framework.util.FileSystemName;
import org.dhltest.framework.web.driver.WebBrowser;

public abstract class ScreenshotImage {
    
    private final File directory;
    
    private final String fileName;

    protected ScreenshotImage( WebBrowser webBrowser,  String folderName,  String mainPath) {
        checkFolderName(folderName);
        this.directory = directory(webBrowser, folderName, mainPath);
        this.fileName = fileName();
    }

    
    public File file() {
        return new File(directory, fileName);
    }

    
    private File directory( WebBrowser webBrowser,  String folderName,  String mainPath) {
        String dateFolderName = DateUtils.convertToString(LocalDateTime.now(), "yyyyMMdd");
        String browserName = webBrowser.name().replace(" ", "");
        File localDirectory = Paths.get(mainPath, dateFolderName, folderName, browserName).toFile();
        if (!localDirectory.exists() && !localDirectory.mkdirs()) {
            AppLogManager.getLogger(getClass().getName(), webBrowser).error("'{}' directory cannot be created for Screenshot image", localDirectory.getAbsolutePath());
        }
        return localDirectory;
    }

    private void checkFolderName( String name) {
        if (name.isEmpty()) {
            throw new RuntimeException("Folder Name cannot be empty");
        }
    }

    
    private String fileName() {
        return new FileSystemName().generateFileName() + ".png";
    }
}
