package xyz.morecraft.dev.simple3d.projection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import xyz.morecraft.dev.simple3d.configuration.EngineConfiguration;
import xyz.morecraft.dev.simple3d.engine.tool.CalculatedPoint;
import xyz.morecraft.dev.simple3d.engine.tool.Camera;
import xyz.morecraft.dev.simple3d.engine.tool.Point;

public abstract class Projection {

    private static final Logger log = LoggerFactory.getLogger(Projection.class);

    private final EngineConfiguration configuration;
    private final Camera camera;

    private double[][] matrix;

    public Projection(EngineConfiguration configuration, Camera camera) {
        this.configuration = configuration;
        this.camera = camera;
    }

    public void initMatrix(double left, double right, double bottom, double top, double near, double far) {
        matrix = getMatrix(left, right, bottom, top, near, far);
    }

    public abstract double[][] getMatrix(double left, double right, double bottom, double top, double near, double far);

    public CalculatedPoint calculatePoint(Point point) {
        point = Point.from(point);

        point.setX(point.getX() - camera.getPosition().getX());
        point.setY(point.getY() - camera.getPosition().getY());
        point.setZ(point.getZ() - camera.getPosition().getZ());

        double x = matrix[0][0] * point.getX() + matrix[0][1] * point.getY() + matrix[0][2] * point.getZ() * -1 + matrix[0][3] * 1;
        double y = matrix[1][0] * point.getX() + matrix[1][1] * point.getY() + matrix[1][2] * point.getZ() * -1 + matrix[1][3] * 1;
        double z = matrix[2][0] * point.getX() + matrix[2][1] * point.getY() + matrix[2][2] * point.getZ() * -1 + matrix[2][3] * 1;
        double w = matrix[3][0] * point.getX() + matrix[3][1] * point.getY() + matrix[3][2] * point.getZ() * -1 + matrix[3][3] * 1;

        x /= w;
        y /= w;
        z /= w;

        double[][] vpm = new double[][]{
                {configuration.getWidth(), 0, 0, 0},
                {0, configuration.getHeight(), 0, 0},
                {0, 0, 1, 0},
                {0, 0, 0, 1}
        };

        x = vpm[0][0] * x + vpm[0][1] * y + vpm[0][2] * z + vpm[0][3] * 1;
        y = vpm[1][0] * x + vpm[1][1] * y + vpm[1][2] * z + vpm[1][3] * 1;
        z = vpm[2][0] * x + vpm[2][1] * y + vpm[2][2] * z + vpm[2][3] * 1;

        CalculatedPoint ret = new CalculatedPoint((int) x, (int) y, z);

        ret.setX(ret.getX() + configuration.getWidth() / 2 );
        ret.setY(ret.getY() + configuration.getHeight() / 4 );

        //log.info("{} -> {}", point, ret);

        return ret;
    }

}
