package xyz.morecraft.dev.simple3d.engine;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import xyz.morecraft.dev.simple3d.configuration.EngineConfiguration;
import xyz.morecraft.dev.simple3d.engine.tool.Camera;
import xyz.morecraft.dev.simple3d.engine.tool.World;
import xyz.morecraft.dev.simple3d.projection.Projection;

import java.awt.*;

@Component
public class Screen {

    private static final Logger log = LoggerFactory.getLogger(Screen.class);

    private final EngineConfiguration configuration;
    private final Projection projection;
    private final Camera camera;
    private final World world;

    @Autowired
    public Screen(EngineConfiguration configuration, Projection projection, Camera camera, World world) {
        this.configuration = configuration;
        this.projection = projection;
        this.camera = camera;
        this.world = world;
    }


    public int[] update(int[] pixels) {
        int n = pixels.length;

        int pos = n / 2 + ((int) camera.getPosition().getX()) - (((int) camera.getPosition().getZ()) * configuration.getWidth()) + configuration.getWidth() / 2;

        if (pos >= 0 && pos < n) {
            pixels[pos] = Color.RED.getRGB();
        }

        return pixels;
    }

}
