package xyz.morecraft.dev.simple3d.engine;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import xyz.morecraft.dev.simple3d.configuration.ControlConfiguration;
import xyz.morecraft.dev.simple3d.engine.tool.Settings;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Component
public final class Listener implements KeyListener {

    private static final Logger log = LoggerFactory.getLogger(Listener.class);

    private final ControlConfiguration configuration;
    private final ScreenShooter screenShooter;
    private final Settings settings;

    private final Set<Integer> keySet;

    @Autowired
    public Listener(ControlConfiguration configuration, ScreenShooter screenShooter, Settings settings) {
        this.configuration = configuration;
        this.screenShooter = screenShooter;
        this.settings = settings;
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
        if (keySet.contains(KeyEvent.VK_ESCAPE)) {
            System.exit(0);
        }
        if (keySet.contains(KeyEvent.VK_W)) {
            settings.getCameraPosition().setZ(settings.getCameraPosition().getZ() + configuration.getMovementSpeed());
        } else if (keySet.contains(KeyEvent.VK_S)) {
            settings.getCameraPosition().setZ(settings.getCameraPosition().getZ() - configuration.getMovementSpeed());
        }
        if (keySet.contains(KeyEvent.VK_A)) {
            settings.getCameraPosition().setX(settings.getCameraPosition().getX() - configuration.getMovementSpeed());
        } else if (keySet.contains(KeyEvent.VK_D)) {
            settings.getCameraPosition().setX(settings.getCameraPosition().getX() + configuration.getMovementSpeed());
        }
        if (keySet.contains(KeyEvent.VK_Q)) {
            settings.getCameraPosition().setY(settings.getCameraPosition().getY() - configuration.getMovementSpeed());
        } else if (keySet.contains(KeyEvent.VK_E)) {
            settings.getCameraPosition().setY(settings.getCameraPosition().getY() + configuration.getMovementSpeed());
        }
        if (keySet.contains(KeyEvent.VK_UP)) {
            settings.getLightSourcePosition().setZ(settings.getLightSourcePosition().getZ() + configuration.getMovementSpeed());
        } else if (keySet.contains(KeyEvent.VK_DOWN)) {
            settings.getLightSourcePosition().setZ(settings.getLightSourcePosition().getZ() - configuration.getMovementSpeed());
        }
        if (keySet.contains(KeyEvent.VK_LEFT)) {
            settings.getLightSourcePosition().setX(settings.getLightSourcePosition().getX() - configuration.getMovementSpeed());
        } else if (keySet.contains(KeyEvent.VK_RIGHT)) {
            settings.getLightSourcePosition().setX(settings.getLightSourcePosition().getX() + configuration.getMovementSpeed());
        }
        if (keySet.contains(KeyEvent.VK_PAGE_UP)) {
            settings.getLightSourcePosition().setY(settings.getLightSourcePosition().getY() - configuration.getMovementSpeed());
        } else if (keySet.contains(KeyEvent.VK_PAGE_DOWN)) {
            settings.getLightSourcePosition().setY(settings.getLightSourcePosition().getY() + configuration.getMovementSpeed());
        }
        if (keySet.contains(KeyEvent.VK_1)) {
            if (isPlus()) {
                settings.setKa(settings.getKa() + configuration.getKFactorChangeSpeed());
            } else if (isMinus()) {
                settings.setKa(settings.getKa() - configuration.getKFactorChangeSpeed());
            }
        }
        if (keySet.contains(KeyEvent.VK_2)) {
            if (isPlus()) {
                settings.setKd(settings.getKd() + configuration.getKFactorChangeSpeed());
            } else if (isMinus()) {
                settings.setKd(settings.getKd() - configuration.getKFactorChangeSpeed());
            }
        }
        if (keySet.contains(KeyEvent.VK_3)) {
            if (isPlus()) {
                settings.setKs(settings.getKs() + configuration.getKFactorChangeSpeed());
            } else if (isMinus()) {
                settings.setKs(settings.getKs() - configuration.getKFactorChangeSpeed());
            }
        }
        if (keySet.contains(KeyEvent.VK_4)) {
            if (isPlus()) {
                settings.setIa(settings.getIa() + configuration.getIFactorChangeSpeed());
            } else if (isMinus()) {
                settings.setIa(settings.getIa() - configuration.getIFactorChangeSpeed());
            }
        }
        if (keySet.contains(KeyEvent.VK_5)) {
            if (isPlus()) {
                settings.setIp(settings.getIp() + configuration.getIFactorChangeSpeed());
            } else if (isMinus()) {
                settings.setIp(settings.getIp() - configuration.getIFactorChangeSpeed());
            }
        }
        if (keySet.contains(KeyEvent.VK_6)) {
            if (isPlus()) {
                settings.setN(settings.getN() + configuration.getNFactorChangeSpeed());
            } else if (isMinus()) {
                settings.setN(settings.getN() - configuration.getNFactorChangeSpeed());
            }
        }
        if (keySet.contains(KeyEvent.VK_7)) {
            if (isPlus()) {
                settings.setC(settings.getC() + configuration.getCFactorChangeSpeed());
            } else if (isMinus()) {
                settings.setC(settings.getC() - configuration.getCFactorChangeSpeed());
            }
        }
    }

    private boolean isPlus() {
        return keySet.contains(KeyEvent.VK_EQUALS);
    }

    private boolean isMinus() {
        return keySet.contains(KeyEvent.VK_MINUS);
    }

}
