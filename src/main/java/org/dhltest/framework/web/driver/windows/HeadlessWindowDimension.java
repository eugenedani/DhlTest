package org.dhltest.framework.web.driver.windows;

import org.openqa.selenium.Dimension;

public class HeadlessWindowDimension extends Dimension {
    public HeadlessWindowDimension() {
        super(1920, 1080);
    }

    public String chromeWindowSize() {
        return String.format("--window-size=%d,%d", width, height);
    }
}
