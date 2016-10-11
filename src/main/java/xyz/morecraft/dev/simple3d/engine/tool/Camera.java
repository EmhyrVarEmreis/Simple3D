package xyz.morecraft.dev.simple3d.engine.tool;

public class Camera {

    private Point position;

    private double horizontalDirectionX;
    private double verticalDirectionX;
    private double horizontalDirectionY;
    private double verticalDirectionY;
    private double horizontalDirectionZ;
    private double verticalDirectionZ;

    public Camera(Point position) {
        this.position = position;
    }

    public Camera() {
        this(new Point(0, 0, 0));
        this.horizontalDirectionX = 0;
        this.verticalDirectionX = 0;
        this.horizontalDirectionY = 0;
        this.verticalDirectionY = 0;
        this.horizontalDirectionZ = 0;
        this.verticalDirectionZ = 0;
    }

    public Point getPosition() {
        return position;
    }

    public void setPosition(Point position) {
        this.position = position;
    }

    public double getHorizontalDirectionX() {
        return horizontalDirectionX;
    }

    public void setHorizontalDirectionX(double horizontalDirectionX) {
        this.horizontalDirectionX = horizontalDirectionX;
    }

    public double getVerticalDirectionX() {
        return verticalDirectionX;
    }

    public void setVerticalDirectionX(double verticalDirectionX) {
        this.verticalDirectionX = verticalDirectionX;
    }

    public double getHorizontalDirectionY() {
        return horizontalDirectionY;
    }

    public void setHorizontalDirectionY(double horizontalDirectionY) {
        this.horizontalDirectionY = horizontalDirectionY;
    }

    public double getVerticalDirectionY() {
        return verticalDirectionY;
    }

    public void setVerticalDirectionY(double verticalDirectionY) {
        this.verticalDirectionY = verticalDirectionY;
    }

    public double getHorizontalDirectionZ() {
        return horizontalDirectionZ;
    }

    public void setHorizontalDirectionZ(double horizontalDirectionZ) {
        this.horizontalDirectionZ = horizontalDirectionZ;
    }

    public double getVerticalDirectionZ() {
        return verticalDirectionZ;
    }

    public void setVerticalDirectionZ(double verticalDirectionZ) {
        this.verticalDirectionZ = verticalDirectionZ;
    }

    @Override
    public String toString() {
        return "Camera{" +
                "position=" + position +
                ", horizontalDirectionX=" + horizontalDirectionX +
                ", verticalDirectionX=" + verticalDirectionX +
                ", horizontalDirectionY=" + horizontalDirectionY +
                ", verticalDirectionY=" + verticalDirectionY +
                ", horizontalDirectionZ=" + horizontalDirectionZ +
                ", verticalDirectionZ=" + verticalDirectionZ +
                '}';
    }

}
