package org.dhltest.framework.web.driver.screenshort;

import java.io.File;
import java.io.InputStream;



public final class PngImage extends Image {

    public PngImage( File pngFile) {
        super(pngFile);
    }

    public PngImage( InputStream pngFile) {
        super(pngFile);
    }

    @Override
    
    protected String formatName() {
        return "png";
    }
}
