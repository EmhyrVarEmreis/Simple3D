package xyz.morecraft.dev.simple3d.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.io.File;
import java.io.IOException;

@SuppressWarnings("WeakerAccess")
public class IO {

    private static final Logger log = LoggerFactory.getLogger(IO.class);

    public static BufferedImage copyImage(BufferedImage image) {
        ColorModel cm = image.getColorModel();
        return new BufferedImage(cm, image.copyData(null), cm.isAlphaPremultiplied(), null);
    }

    public static void saveImagePng(BufferedImage image, String path) throws IOException {
        File f = new File(path);
        ImageIO.write(image, "png", f);
        log.info("File [{}] saved", f.getAbsolutePath());
    }

}
