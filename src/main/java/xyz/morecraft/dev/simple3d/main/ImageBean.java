package xyz.morecraft.dev.simple3d.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import xyz.morecraft.dev.simple3d.configuration.WindowConfiguration;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

@Component
public class ImageBean {

    private final BufferedImage image;
    private int[] pixels;

    @Autowired
    public ImageBean(WindowConfiguration configuration) {
        image = new BufferedImage(configuration.getWidth(), configuration.getHeight(), BufferedImage.TYPE_INT_RGB);
        pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
    }

    public BufferedImage getImage() {
        return image;
    }

    public int[] getPixels() {
        return pixels;
    }

}
