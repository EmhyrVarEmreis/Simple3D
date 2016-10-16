package xyz.morecraft.dev.simple3d.engine.projection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import xyz.morecraft.dev.simple3d.configuration.WindowConfiguration;
import xyz.morecraft.dev.simple3d.engine.projection.matrix.MatrixInitializer;
import xyz.morecraft.dev.simple3d.engine.tool.CalculatedPoint;
import xyz.morecraft.dev.simple3d.engine.tool.Camera;
import xyz.morecraft.dev.simple3d.engine.tool.Point;

@Component
public final class Projection {

    private static final Logger log = LoggerFactory.getLogger(Projection.class);

    private final WindowConfiguration configuration;
    private final Camera camera;

    private double[][] matrix;
    private double[][] vpm;

    @Autowired
    public Projection(WindowConfiguration configuration, Camera camera, MatrixInitializer matrixInitializer) {
        this.configuration = configuration;
        this.camera = camera;

        this.matrix = matrixInitializer.initMatrix();

        this.vpm = new double[][]{
                {configuration.getWidth(), 0, 0, 0},
                {0, configuration.getHeight(), 0, 0},
                {0, 0, 1, 0},
                {0, 0, 0, 1}
        };
    }

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

        // Translate by camera position
        x -= cx;
        y -= cy;
        z -= cz;

        // Rotate by angles
        double dx = cos(ay) * (sin(az) * (y - cy) + cos(az) * (x - cx)) - sin(ay) * (z - cz);
        double dy = sin(ax) * (cos(ay) * (z - cz) + sin(ay) * (sin(az) * (y - cy) + cos(az) * (x - cx))) + cos(ax) * (cos(az) * (y - cy) - sin(az) * (x - cx));
        double dz = cos(ax) * (cos(ay) * (z - cz) + sin(ay) * (sin(az) * (y - cy) + cos(az) * (x - cx))) - sin(ax) * (cos(az) * (y - cy) - sin(az) * (x - cx));

        x = dx;
        y = dy;
        z = dz;

        // Multiply through projection matrix
        x = matrix[0][0] * x + matrix[0][1] * y + matrix[0][2] * z * -1 + matrix[0][3] * 1;
        y = matrix[1][0] * x + matrix[1][1] * y + matrix[1][2] * z * -1 + matrix[1][3] * 1;
        z = matrix[2][0] * x + matrix[2][1] * y + matrix[2][2] * z * -1 + matrix[2][3] * 1;
        w = matrix[3][0] * x + matrix[3][1] * y + matrix[3][2] * z * -1 + matrix[3][3] * 1;

        // Divide by w factor
        x /= w;
        y /= w;
        z /= w;

        // Change normalized coordinates to screen coordinates
        x = vpm[0][0] * x + vpm[0][1] * y + vpm[0][2] * z + vpm[0][3] * 1;
        y = vpm[1][0] * x + vpm[1][1] * y + vpm[1][2] * z + vpm[1][3] * 1;
        z = vpm[2][0] * x + vpm[2][1] * y + vpm[2][2] * z + vpm[2][3] * 1;

        CalculatedPoint ret = new CalculatedPoint((int) x, (int) y, z);

        // Center view
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
