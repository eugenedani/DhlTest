package org.dhltest.framework.web.driver.screenshort;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import org.dhltest.framework.ActionWasNotDoneException;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.OutputType;

public abstract class Image extends Screenshot {

    private final BufferedImage bufferedImage;

    public Image(File imageFile) {
        this.bufferedImage = bufferedImage(imageFile);
        checkFile(imageFile);
    }

    public Image(InputStream imageFile) {
        try {
            this.bufferedImage = ImageIO.read(imageFile);
        } catch (IOException e) {
            throw new ActionWasNotDoneException("Cannot convert InputStream to an Image", e);
        }
    }

    protected abstract String formatName();

    @Override
    protected File screenshot() {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            ImageIO.write(bufferedImage, formatName(), baos);
            return OutputType.FILE.convertFromPngBytes(baos.toByteArray());
        } catch (IOException e) {
            throw new ActionWasNotDoneException("Cannot convert BufferedImage to byte[]", e);
        }
    }

    private void checkFile(File file) {
        String fileEnding = "." + formatName();
        if (file.getName().toLowerCase().endsWith(fileEnding)) {
            return;
        }

        throw new RuntimeException(file.getAbsolutePath() + " file is not " + fileEnding + " file");
    }

    private BufferedImage bufferedImage(File file) {
        if (!file.exists()) {
            throw new NotFoundException(file.getAbsolutePath() + " file was not found");
        }

        try {
            return ImageIO.read(file);
        } catch (IOException e) {
            throw new ActionWasNotDoneException("Cannot convert '" + file.getAbsolutePath() + "' file to an Image", e);
        }
    }
}
