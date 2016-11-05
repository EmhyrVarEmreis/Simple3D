package xyz.morecraft.dev.simple3d.engine;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import xyz.morecraft.dev.simple3d.main.ImageBean;
import xyz.morecraft.dev.simple3d.util.IO;

@Component
public class ScreenShooter {

    private static final Logger log = LoggerFactory.getLogger(ScreenShooter.class);

    private final ImageBean imageBean;

    @Autowired
    public ScreenShooter(ImageBean imageBean) {
        this.imageBean = imageBean;
    }

    public void takeScreenShoot() {
        new Thread(() -> {
            try {
                IO.saveImagePng(IO.copyImage(imageBean.getImage()), System.currentTimeMillis() + ".png");
            } catch (Exception e) {
                log.error("Error occurred during taking screenshot", e);
            }
        }).start();
    }

}
