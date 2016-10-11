package xyz.morecraft.dev.simple3d.projection;

import xyz.morecraft.dev.simple3d.configuration.EngineConfiguration;
import xyz.morecraft.dev.simple3d.engine.tool.Camera;

public class PerspectiveProjection extends Projection {

    public PerspectiveProjection(EngineConfiguration configuration, Camera camera) {
        super(configuration, camera);
    }

    @Override
    public double[][] getMatrix(double left, double right, double bottom, double top, double near, double far) {
        return new double[][]{
                {2 * near / (right - left), 0, (right + left) / (right - left), 0},
                {0, 2 * near / (top - bottom), (top + bottom) / (top - bottom), 0},
                {0, 0, -(far + near) / (far - near), -2 * far * near / (far - near)},
                {0, 0, -1, 0}
        };
    }

}
