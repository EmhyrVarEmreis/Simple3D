package xyz.morecraft.dev.simple3d.engine.tool;

public class CalculatedPoint {

    private int x;
    private int y;
    private double z;
    private double w;

    public CalculatedPoint(int x, int y, double z, double w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public double getZ() {
        return z;
    }

    public void setZ(double z) {
        this.z = z;
    }

    public double getW() {
        return w;
    }

    public void setW(double w) {
        this.w = w;
    }

    public static CalculatedPoint from(CalculatedPoint old) {
        return new CalculatedPoint(old.x, old.y, old.z, old.w);
    }

    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                ", w=" + w +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CalculatedPoint that = (CalculatedPoint) o;

        if (x != that.x) {
            return false;
        }
        if (y != that.y) {
            return false;
        }
        if (Double.compare(that.z, z) != 0) {
            return false;
        }
        return Double.compare(that.w, w) == 0;

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = x;
        result = 31 * result + y;
        temp = Double.doubleToLongBits(z);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(w);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

}
