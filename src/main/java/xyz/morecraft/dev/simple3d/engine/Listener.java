package xyz.morecraft.dev.simple3d.engine;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import xyz.morecraft.dev.simple3d.configuration.EngineConfiguration;
import xyz.morecraft.dev.simple3d.engine.tool.Camera;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class Listener implements KeyListener {

    private static final Logger log = LoggerFactory.getLogger(Listener.class);

    private final EngineConfiguration configuration;
    private final Camera camera;

    private final Set<Integer> keySet;

    @Autowired
    public Listener(EngineConfiguration configuration, Camera camera) {
        this.configuration = configuration;
        this.camera = camera;
        this.keySet = ConcurrentHashMap.newKeySet();
    }

    @Override
    public void keyTyped(KeyEvent e) {
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
    }

}
