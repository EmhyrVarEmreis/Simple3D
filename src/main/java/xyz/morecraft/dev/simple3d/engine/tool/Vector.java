package xyz.morecraft.dev.simple3d.engine.tool;

public class Vector {

    private double x;
    private double y;
    private double z;

    public Vector(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vector(Point p1, Point p2) {
        this(p2.getX() - p1.getX(), p2.getY() - p1.getY(), p2.getZ() - p1.getZ());
    }

    public Vector normalize() {
        double norma = norma();
        x = x / norma;
        y = y / norma;
        z = z / norma;
        return new Vector(x, y, z);
    }

    public double norma() {
        return Math.sqrt(dotProduct(this));
    }

    public double dotProduct(Vector v) {
        return x * v.x + y * v.y + z * v.z;
    }

    public Vector mul(double d) {
        double x1 = x * d;
        double y1 = y * d;
        double z1 = z * d;
        return new Vector(x1, y1, z1);
    }

    public Vector dif(Vector v) {
        double x1 = x - v.x;
        double y1 = y - v.y;
        double z1 = z - v.z;
        return new Vector(x1, y1, z1);
    }

    @Override
    public String toString() {
        return "[" + x + " , " + y + " , " + z + "]";
    }

}
