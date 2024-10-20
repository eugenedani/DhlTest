package org.dhltest.framework.web.driver.screenshort;

import java.io.File;
import java.io.IOException;
import java.nio.file.CopyOption;
import java.nio.file.Files;

import org.apache.logging.log4j.Logger;

import org.openqa.selenium.WebDriverException;

import org.dhltest.framework.log.AppLogManager;

public abstract class Screenshot {
    
    private final Logger logManager = AppLogManager.getLogger(getClass().getName());

    
    protected abstract File screenshot();

    public void saveTo( ScreenshotImage image,  CopyOption... options) {
        saveTo(image.file(), options);
    }

    private void saveTo( File imageFile,  CopyOption... options) {
        if (!available(imageFile)) {
            logManager.error("'{}' path is not correct for making screenshot", imageFile.getAbsolutePath());
            return;
        }

        try {
            Files.move(screenshot().toPath(), imageFile.toPath(), options);
            logManager.info("Screenshot file path: {}", imageFile.getAbsolutePath());
        } catch (WebDriverException ex) {
            logManager.error("Screenshot could not be taken. Exception: ", ex);
        } catch (IOException ex) {
            logManager.error("Screenshot file cannot be moved to {}. Exception: {}", imageFile.getAbsolutePath(), ex.getMessage());
        }
    }

    private boolean available( File imageFile) {
        return !imageFile.isDirectory() && imageFile.getAbsoluteFile().getParentFile().exists();
    }
}
