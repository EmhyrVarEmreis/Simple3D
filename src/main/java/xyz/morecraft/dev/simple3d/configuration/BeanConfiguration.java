package xyz.morecraft.dev.simple3d.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import xyz.morecraft.dev.simple3d.engine.projection.matrix.MatrixInitializer;
import xyz.morecraft.dev.simple3d.engine.projection.matrix.PerspectiveProjectionMatrixInitializer;
import xyz.morecraft.dev.simple3d.engine.tool.Camera;
import xyz.morecraft.dev.simple3d.engine.tool.ModelFactory;
import xyz.morecraft.dev.simple3d.engine.tool.Point;
import xyz.morecraft.dev.simple3d.engine.tool.World;

import java.awt.*;

@Configuration
public class BeanConfiguration {

    @Primary
    @Bean(name = "defaultWorld")
    public World defaultWorld() {
        World world = new World();

        world.addModel(ModelFactory.createHorizontalSquareMesh(new Point(-20, 5, 20), new Point(25, 5, 50), 0.75));
        world.addModel(ModelFactory.createCube(new Point(5, 5, 30), 5, Color.RED));
        world.addModel(ModelFactory.createCube(new Point(15, 5, 30), 5, Color.BLUE));
        world.addModel(ModelFactory.createCube(new Point(16, 6, 31), 3, Color.DARK_GRAY));
        world.addModel(ModelFactory.createCube(new Point(-15, 5, 35), 7, Color.GRAY));
        world.addModel(ModelFactory.createCube(new Point(-5, 5, 30), 4, Color.ORANGE));

        return world;
    }

    @Primary
    @Bean(name = "defaultCamera")
    public Camera defaultCamera() {
        return new Camera(new Point(0, 10, 0));
    }

    @Primary
    @Bean(name = "defaultProjectionMatrix")
    @Autowired
    public MatrixInitializer defaultScreen(MatrixConfiguration matrixConfiguration, Camera camera) {
        return new PerspectiveProjectionMatrixInitializer(matrixConfiguration, camera);
    }

}
