package org.dhltest.framework.web.driver.screenshort;

import java.io.File;
import java.io.InputStream;



public final class JpgImage extends Image {

    public JpgImage( File jpgFile) {
        super(jpgFile);
    }

    public JpgImage( InputStream jpgFile) {
        super(jpgFile);
    }

    @Override
    
    protected String formatName() {
        return "jpg";
    }
}
