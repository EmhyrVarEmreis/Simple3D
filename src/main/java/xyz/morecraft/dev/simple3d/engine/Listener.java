package xyz.morecraft.dev.simple3d.engine;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import xyz.morecraft.dev.simple3d.configuration.ControlConfiguration;
import xyz.morecraft.dev.simple3d.engine.tool.Camera;
import xyz.morecraft.dev.simple3d.main.RenderThread;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Component
public final class Listener implements KeyListener {

    private static final Logger log = LoggerFactory.getLogger(Listener.class);

    private final ControlConfiguration configuration;
    private final ScreenShooter screenShooter;
    private final Camera camera;

    private final Set<Integer> keySet;

    @Autowired
    public Listener(ControlConfiguration configuration, ScreenShooter screenShooter, Camera camera) {
        this.configuration = configuration;
        this.screenShooter = screenShooter;
        this.camera = camera;
        this.keySet = ConcurrentHashMap.newKeySet();
    }

    @Override
    public void keyTyped(KeyEvent e) {
        if (Character.toUpperCase(e.getKeyChar()) == KeyEvent.VK_H) {
            screenShooter.takeScreenShoot();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        keySet.add(e.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent e) {
        keySet.remove(e.getKeyCode());
    }

    public void update() {
        //log.debug("{}", keySet.stream().map(Object::toString).collect(Collectors.joining(", ")));
        if (keySet.contains(KeyEvent.VK_ESCAPE)) {
            System.exit(0);
        }
        if (keySet.contains(KeyEvent.VK_W)) {
            camera.getPosition().setZ(camera.getPosition().getZ() + configuration.getMovementSpeed());
        } else if (keySet.contains(KeyEvent.VK_S)) {
            camera.getPosition().setZ(camera.getPosition().getZ() - configuration.getMovementSpeed());
        }
        if (keySet.contains(KeyEvent.VK_A)) {
            camera.getPosition().setX(camera.getPosition().getX() - configuration.getMovementSpeed());
        } else if (keySet.contains(KeyEvent.VK_D)) {
            camera.getPosition().setX(camera.getPosition().getX() + configuration.getMovementSpeed());
        }
        if (keySet.contains(KeyEvent.VK_Q)) {
            camera.getPosition().setY(camera.getPosition().getY() - configuration.getMovementSpeed());
        } else if (keySet.contains(KeyEvent.VK_E)) {
            camera.getPosition().setY(camera.getPosition().getY() + configuration.getMovementSpeed());
        }
        if (keySet.contains(KeyEvent.VK_UP)) {
            camera.setAngleX(camera.getAngleX() - configuration.getRotationSpeed());
        } else if (keySet.contains(KeyEvent.VK_DOWN)) {
            camera.setAngleX(camera.getAngleX() + configuration.getRotationSpeed());
        }
        if (keySet.contains(KeyEvent.VK_RIGHT)) {
            camera.setAngleY(camera.getAngleY() - configuration.getRotationSpeed());
        } else if (keySet.contains(KeyEvent.VK_LEFT)) {
            camera.setAngleY(camera.getAngleY() + configuration.getRotationSpeed());
        }
        if (keySet.contains(KeyEvent.VK_COMMA)) {
            camera.setAngleZ(camera.getAngleZ() - configuration.getRotationSpeed());
        } else if (keySet.contains(KeyEvent.VK_PERIOD)) {
            camera.setAngleZ(camera.getAngleZ() + configuration.getRotationSpeed());
        }
        if (keySet.contains(KeyEvent.VK_MINUS)) {
            camera.setZoom(camera.getZoom() - configuration.getZoomSpeed());
            RenderThread.NEEDS_RECALC = true;
        } else if (keySet.contains(KeyEvent.VK_EQUALS)) {
            camera.setZoom(camera.getZoom() + configuration.getZoomSpeed());
            RenderThread.NEEDS_RECALC = true;
        }
    }

}
