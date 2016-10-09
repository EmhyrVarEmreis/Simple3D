package xyz.morecraft.dev.simple3d.projection;

public abstract class Projection {

    public abstract double[][] getMatrix(double left, double right, double bottom, double top, double near, double far);



}
