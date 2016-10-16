package xyz.morecraft.dev.simple3d.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import xyz.morecraft.dev.simple3d.configuration.EngineConfiguration;
import xyz.morecraft.dev.simple3d.engine.Listener;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

@Component
public class Window extends JFrame {

    private BufferedImage image;
    private int[] pixels;

    @Autowired
    public Window(EngineConfiguration configuration, Listener listener) throws HeadlessException {
        setSize(configuration.getWidth(), configuration.getHeight());
        setResizable(false);
        setTitle("Simple3D");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBackground(Color.black);
        setLocationRelativeTo(null);

        addKeyListener(listener);

        image = new BufferedImage(configuration.getWidth(), configuration.getHeight(), BufferedImage.TYPE_INT_RGB);
        pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();

        if (configuration.isFullscreen()) {
            setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);
            setUndecorated(true);
        }
    }

    public BufferedImage getImage() {
        return image;
    }

    public int[] getPixels() {
        return pixels;
    }

    public void render() {
        BufferStrategy bs = getBufferStrategy();
        if (bs == null) {
            createBufferStrategy(3);
            return;
        }
        bs.getDrawGraphics().drawImage(image, 0, 0, image.getWidth(), image.getHeight(), null);
        bs.show();
    }

}
