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

    public void initMatrix(final double fovy, final double aspect, final double near, final double far) {
        matrix = getMatrix(fovy, aspect, near, far);
        //matrix = getMatrix2(50, configuration.getWidth() * 1.0 / configuration.getHeight(), 1, 100);
    }

    public abstract double[][] getMatrix(final double fovy, final double aspect, final double near, final double far);

    public CalculatedPoint calculatePoint(Point point) {
        double x, y, z, w, cx, cy, cz, ax, ay, az;

        x = point.getX();
        y = point.getY();
        z = point.getZ();
        cx = camera.getPosition().getX();
        cy = camera.getPosition().getY();
        cz = camera.getPosition().getZ();
        ax = camera.getAngleX();
        ay = camera.getAngleY();
        az = camera.getAngleZ();

        x -= cx;
        y -= cy;
        z -= cz;

        double dx = cos(ay) * (sin(az) * (y - cy) + cos(az) * (x - cx)) - sin(ay) * (z - cz);
        double dy = sin(ax) * (cos(ay) * (z - cz) + sin(ay) * (sin(az) * (y - cy) + cos(az) * (x - cx))) + cos(ax) * (cos(az) * (y - cy) - sin(az) * (x - cx));
        double dz = cos(ax) * (cos(ay) * (z - cz) + sin(ay) * (sin(az) * (y - cy) + cos(az) * (x - cx))) - sin(ax) * (cos(az) * (y - cy) - sin(az) * (x - cx));

        x = dx;
        y = dy;
        z = dz;

        x = matrix[0][0] * x + matrix[0][1] * y + matrix[0][2] * z * -1 + matrix[0][3] * 1;
        y = matrix[1][0] * x + matrix[1][1] * y + matrix[1][2] * z * -1 + matrix[1][3] * 1;
        z = matrix[2][0] * x + matrix[2][1] * y + matrix[2][2] * z * -1 + matrix[2][3] * 1;
        w = matrix[3][0] * x + matrix[3][1] * y + matrix[3][2] * z * -1 + matrix[3][3] * 1;

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

        ret.setX(ret.getX() + configuration.getWidth() / 2 - (int) camera.getPosition().getX());
        ret.setY(ret.getY() + configuration.getHeight() / 2 - (int) camera.getPosition().getY());

        //log.info("{} -> {}", point, ret);

        return ret;
    }

    private double sin(double s) {
        return Math.sin(Math.toRadians(s));
    }

    private double cos(double s) {
        return Math.cos(Math.toRadians(s));
    }

}
