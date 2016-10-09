package xyz.morecraft.dev.simple3d;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import xyz.morecraft.dev.simple3d.main.Main;

@SpringBootApplication
@EnableConfigurationProperties
@ComponentScan(basePackages = {"xyz.morecraft.dev.simple3d"})
public class Application {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = new SpringApplicationBuilder(Application.class)
                .headless(false)
                .run(args);

        Main main = context.getBean(Main.class);

        main.run();
    }

}
