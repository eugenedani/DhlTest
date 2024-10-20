package org.dhltest.framework.web.driver.screenshort;

import java.nio.file.Paths;



import org.dhltest.framework.CurrentPath;
import org.dhltest.framework.web.driver.WebBrowser;

public final class DefectImage extends ScreenshotImage {
    public DefectImage( WebBrowser webBrowser,  String folderName) {
        super(webBrowser, folderName, Paths.get(CurrentPath.getPath(), "ErrorsScreenshots").toString());
    }
}
