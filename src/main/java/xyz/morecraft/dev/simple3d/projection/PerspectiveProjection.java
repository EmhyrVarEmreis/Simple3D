package xyz.morecraft.dev.simple3d.projection;

import xyz.morecraft.dev.simple3d.configuration.EngineConfiguration;
import xyz.morecraft.dev.simple3d.engine.tool.Camera;

public class PerspectiveProjection extends Projection {

    public PerspectiveProjection(EngineConfiguration configuration, Camera camera) {
        super(configuration, camera);
    }

    @Override
    public double[][] getMatrix(final double fovy, final double aspect, final double near, final double far) {
        double y2 = near * Math.tan(Math.toRadians(fovy / 2));
        double y1 = -y2;
        double x1 = y1 * aspect;
        double x2 = y2 * aspect;
        return getFrustum(x1, x2, y1, y2, near, far);
    }

    public double[][] getFrustum(final double left, final double right, final double bottom, final double top, final double near, final double far) {
        return new double[][]{
                {2 * near / (right - left), 0.0f, 0.0f, 0.0f},
                {0.0f, 2 * near / (top - bottom), 0.0f, 0.0f},
                {(right + left) / (right - left), (top + bottom) / (top - bottom), (near + far) / (near - far), -1.0f},
                {0.0f, 0.0f, 2 * near * far / (near - far), 0.0f}
        };
    }

}
