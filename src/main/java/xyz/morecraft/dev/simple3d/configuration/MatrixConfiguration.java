package xyz.morecraft.dev.simple3d.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "matrix")
public class MatrixConfiguration {

    private double fieldOfViewAngle = 50.0;
    private double aspectRatio = -1;
    private double distanceToNearClippingPlane = 1.0;
    private double distanceToFarClippingPlane = 100.0;

    @Autowired
    public MatrixConfiguration(WindowConfiguration windowConfiguration) {
        if (this.aspectRatio == -1) {
            this.aspectRatio = windowConfiguration.getWidth() * 1.0 / windowConfiguration.getHeight();
        }
    }

    public double getFieldOfViewAngle() {
        return fieldOfViewAngle;
    }

    public void setFieldOfViewAngle(double fieldOfViewAngle) {
        this.fieldOfViewAngle = fieldOfViewAngle;
    }

    public double getAspectRatio() {
        return aspectRatio;
    }

    public void setAspectRatio(double aspectRatio) {
        this.aspectRatio = aspectRatio;
    }

    public double getDistanceToNearClippingPlane() {
        return distanceToNearClippingPlane;
    }

    public void setDistanceToNearClippingPlane(double distanceToNearClippingPlane) {
        this.distanceToNearClippingPlane = distanceToNearClippingPlane;
    }

    public double getDistanceToFarClippingPlane() {
        return distanceToFarClippingPlane;
    }

    public void setDistanceToFarClippingPlane(double distanceToFarClippingPlane) {
        this.distanceToFarClippingPlane = distanceToFarClippingPlane;
    }

}
