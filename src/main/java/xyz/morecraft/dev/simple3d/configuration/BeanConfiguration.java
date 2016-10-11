package xyz.morecraft.dev.simple3d.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import xyz.morecraft.dev.simple3d.engine.DrawingScreen;
import xyz.morecraft.dev.simple3d.engine.Screen;
import xyz.morecraft.dev.simple3d.engine.tool.Camera;
import xyz.morecraft.dev.simple3d.engine.tool.ModelFactory;
import xyz.morecraft.dev.simple3d.engine.tool.Point;
import xyz.morecraft.dev.simple3d.engine.tool.World;
import xyz.morecraft.dev.simple3d.main.Window;
import xyz.morecraft.dev.simple3d.projection.PerspectiveProjection;
import xyz.morecraft.dev.simple3d.projection.Projection;

@Configuration
public class BeanConfiguration {

    @Primary
    @Bean(name = "defaultProjection")
    @Autowired
    public Projection perspectiveProjection(Camera camera, EngineConfiguration configuration) {
        return new PerspectiveProjection(configuration, camera);
    }

    @Primary
    @Bean(name = "defaultWorld")
    public World defaultWorld() {
        World world = new World();

        world.addModel(ModelFactory.createCube(new Point(5, 5, 5), 5));
        world.addModel(ModelFactory.createCube(new Point(15, 5, 5), 5));

        return world;
    }

    @Primary
    @Bean(name = "defaultCamera")
    public Camera defaultCamera() {
        return new Camera();
    }

    @Primary
    @Bean(name = "defaultScreen")
    @Autowired
    public Screen defaultScreen(EngineConfiguration configuration, Projection projection, Camera camera, Window window, World world) {
        return new DrawingScreen(configuration, projection, camera, window, world);
    }

}
