package xyz.morecraft.dev.simple3d.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import xyz.morecraft.dev.simple3d.engine.tool.Camera;
import xyz.morecraft.dev.simple3d.engine.tool.World;
import xyz.morecraft.dev.simple3d.projection.PerspectiveProjection;
import xyz.morecraft.dev.simple3d.projection.Projection;

@Configuration
public class BeanConfiguration {

    @Primary
    @Bean(name = "perspectiveProjection")
    public Projection perspectiveProjection() {
        return new PerspectiveProjection();
    }

    @Primary
    @Bean(name = "defaultWorld")
    public World defaultWorld() {
        return new World();
    }

    @Primary
    @Bean(name = "defaultCamera")
    public Camera defaultCamera() {
        return new Camera();
    }

}
