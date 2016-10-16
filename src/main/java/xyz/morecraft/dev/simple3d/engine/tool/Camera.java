package xyz.morecraft.dev.simple3d.engine.tool;

public class Camera {

    private Point position;

    private double angleX;
    private double angleY;
    private double angleZ;

    private double zoom;

    public Camera(Point position) {
        this.position = position;
        this.angleX = 0;
        this.angleY = 0;
        this.angleZ = 0;
        this.zoom = 1;
    }

    public Camera() {
        this(new Point(0, 0, 0));
    }

    public Point getPosition() {
        return position;
    }

    public void setPosition(Point position) {
        this.position = position;
    }

    public double getAngleX() {
        return angleX;
    }

    public void setAngleX(double angleX) {
        this.angleX = angleX;
    }

    public double getAngleY() {
        return angleY;
    }

    public void setAngleY(double angleY) {
        this.angleY = angleY;
    }

    public double getAngleZ() {
        return angleZ;
    }

    public void setAngleZ(double angleZ) {
        this.angleZ = angleZ;
    }

    public double getZoom() {
        return zoom;
    }

    public void setZoom(double zoom) {
        this.zoom = zoom;
    }

    @Override
    public String toString() {
        return "Camera{" +
                "position=" + position +
                ", angleX=" + angleX +
                ", angleY=" + angleY +
                ", angleZ=" + angleZ +
                ", zoom=" + zoom +
                '}';
    }

}
