package xyz.morecraft.dev.simple3d.engine;

import xyz.morecraft.dev.simple3d.configuration.EngineConfiguration;
import xyz.morecraft.dev.simple3d.engine.tool.Camera;
import xyz.morecraft.dev.simple3d.engine.tool.World;
import xyz.morecraft.dev.simple3d.main.Window;
import xyz.morecraft.dev.simple3d.projection.Projection;

public abstract class Screen {

    private final EngineConfiguration configuration;
    private final Projection projection;
    private final Camera camera;
    private final Window window;
    private final World world;

    public Screen(EngineConfiguration configuration, Projection projection, Camera camera, Window window, World world) {
        this.configuration = configuration;
        this.projection = projection;
        this.camera = camera;
        this.window = window;
        this.world = world;
    }

    public EngineConfiguration getConfiguration() {
        return configuration;
    }

    public Projection getProjection() {
        return projection;
    }

    public Camera getCamera() {
        return camera;
    }

    public Window getWindow() {
        return window;
    }

    public World getWorld() {
        return world;
    }

    public abstract void update();

}
