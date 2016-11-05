package xyz.morecraft.dev.simple3d.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import xyz.morecraft.dev.simple3d.configuration.WindowConfiguration;
import xyz.morecraft.dev.simple3d.engine.Listener;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

@Component
public final class Window extends JFrame {

    private final BufferedImage image;

    private final WindowConfiguration configuration;
    private final Listener listener;

    @Autowired
    public Window(WindowConfiguration configuration, ImageBean imageBean, Listener listener) throws HeadlessException {
        this.configuration = configuration;
        this.image = imageBean.getImage();
        this.listener = listener;
    }

    void init() {
        setSize(configuration.getWidth(), configuration.getHeight());
        setResizable(false);
        setTitle("Simple3D");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBackground(Color.black);
        setLocationRelativeTo(null);

        addKeyListener(listener);

        if (configuration.isFullscreen()) {
            setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);
            setUndecorated(true);
        }
    }

    void render() {
        BufferStrategy bs = getBufferStrategy();
        if (bs == null) {
            createBufferStrategy(3);
            return;
        }
        bs.getDrawGraphics().drawImage(image, 0, 0, image.getWidth(), image.getHeight(), null);
        bs.show();
    }

}
