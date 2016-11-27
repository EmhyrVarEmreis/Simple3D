package xyz.morecraft.dev.simple3d.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import xyz.morecraft.dev.simple3d.engine.tool.PhongSphere;
import xyz.morecraft.dev.simple3d.engine.tool.Point;
import xyz.morecraft.dev.simple3d.engine.tool.Settings;
import xyz.morecraft.dev.simple3d.engine.tool.World;

@Configuration
public class BeanConfiguration {

    @Primary
    @Bean(name = "defaultWorld")
    public World defaultWorld() {
        World world = new World();

        world.addPhongSphere(
                new PhongSphere(
                        0, "PS1", new Point(0, 0, 3), 1.5
                )
        );

        return world;
    }

    @Primary
    @Bean(name = "defaultCamera")
    public Settings defaultCamera() {
        return new Settings(
                new Point(0, 0, 0),
                new Point(-3, 3, -5)
        );
    }

}
