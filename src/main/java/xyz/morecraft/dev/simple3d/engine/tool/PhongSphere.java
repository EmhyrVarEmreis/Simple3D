package xyz.morecraft.dev.simple3d.engine.tool;

public class PhongSphere extends Entity {

    private double radius;

    public PhongSphere(int id, String name, Point position, double radius) {
        super(id, name, position);
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public Point intersection(Point p0, Point p1) {
        double dx = p1.getX() - p0.getX();
        double dy = p1.getY() - p0.getY();
        double dz = p1.getZ() - p0.getZ();
        double a = dx * dx + dy * dy + dz * dz;
        double b = 2 * (dx * (p0.getX() - getPosition().getX()) + dy * (p0.getY() - getPosition().getY()) + dz * (p0.getZ() - getPosition().getZ()));
        double c = (p0.getX() - getPosition().getX()) * (p0.getX() - getPosition().getX()) + (p0.getY() - getPosition().getY()) * (p0.getY() - getPosition().getY()) + (p0.getZ() - getPosition().getZ()) * (p0.getZ() - getPosition().getZ()) - radius * radius;
        double d = b * b - 4 * a * c;
        if (d < 0) {
            return null;
        } else if (d == 0) {
            double t = -b / 2 * a;
            return new Point(
                    p0.getX() + t * (p1.getX() - p0.getX()),
                    p0.getY() + t * (p1.getY() - p0.getY()),
                    p0.getZ() + t * (p1.getZ() - p0.getZ()));
        } else {
            double t1 = (-b - Math.sqrt(d)) / (2 * a);
            double t2 = (-b + Math.sqrt(d)) / (2 * a);
            double t = Math.min(t1, t2);
            return new Point(
                    p0.getX() + t * (p1.getX() - p0.getX()),
                    p0.getY() + t * (p1.getY() - p0.getY()),
                    p0.getZ() + t * (p1.getZ() - p0.getZ())
            );
        }

    }

}
