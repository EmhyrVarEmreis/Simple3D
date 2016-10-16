package xyz.morecraft.dev.simple3d.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "engine")
public class EngineConfiguration {

    private int maxFps = 30;
    private int maxControlRefresh = 100;

    public int getMaxFps() {
        return maxFps;
    }

    public void setMaxFps(int maxFps) {
        this.maxFps = maxFps;
    }

    public int getMaxControlRefresh() {
        return maxControlRefresh;
    }

    public void setMaxControlRefresh(int maxControlRefresh) {
        this.maxControlRefresh = maxControlRefresh;
    }

}
