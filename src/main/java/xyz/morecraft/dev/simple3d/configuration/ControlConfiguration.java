package xyz.morecraft.dev.simple3d.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "control")
public class ControlConfiguration {

    private double movementSpeed = 0.05;
    private double kFactorChangeSpeed = 0.05;
    private double iFactorChangeSpeed = 0.05;
    private double nFactorChangeSpeed = 0.05;
    private double cFactorChangeSpeed = 0.05;

    public double getMovementSpeed() {
        return movementSpeed;
    }

    public void setMovementSpeed(double movementSpeed) {
        this.movementSpeed = movementSpeed;
    }

    public double getKFactorChangeSpeed() {
        return kFactorChangeSpeed;
    }

    public void setKFactorChangeSpeed(double kFactorChangeSpeed) {
        this.kFactorChangeSpeed = kFactorChangeSpeed;
    }

    public double getIFactorChangeSpeed() {
        return iFactorChangeSpeed;
    }

    public void setIFactorChangeSpeed(double iFactorChangeSpeed) {
        this.iFactorChangeSpeed = iFactorChangeSpeed;
    }

    public double getNFactorChangeSpeed() {
        return nFactorChangeSpeed;
    }

    public void setNFactorChangeSpeed(double nFactorChangeSpeed) {
        this.nFactorChangeSpeed = nFactorChangeSpeed;
    }

    public double getCFactorChangeSpeed() {
        return cFactorChangeSpeed;
    }

    public void setCFactorChangeSpeed(double cFactorChangeSpeed) {
        this.cFactorChangeSpeed = cFactorChangeSpeed;
    }

}
