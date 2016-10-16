package xyz.morecraft.dev.simple3d.engine.projection.matrix;

import xyz.morecraft.dev.simple3d.configuration.MatrixConfiguration;

public final class PerspectiveProjectionMatrixInitializer implements MatrixInitializer {

    private final MatrixConfiguration matrixConfiguration;

    public PerspectiveProjectionMatrixInitializer(MatrixConfiguration matrixConfiguration) {
        this.matrixConfiguration = matrixConfiguration;
    }

    @Override
    public double[][] initMatrix() {
        return getMatrix(
                matrixConfiguration.getFieldOfViewAngle(),
                matrixConfiguration.getAspectRatio(),
                matrixConfiguration.getDistanceToNearClippingPlane(),
                matrixConfiguration.getDistanceToFarClippingPlane()
        );
    }

    private double[][] getMatrix(final double fieldOfViewAngle, final double aspectRatio, final double distanceToNearClippingPlane, final double distanceToFarClippingPlane) {
        double y2 = distanceToNearClippingPlane * Math.tan(Math.toRadians(fieldOfViewAngle / 2));
        double y1 = -y2;
        double x1 = y1 * aspectRatio;
        double x2 = y2 * aspectRatio;
        return getFrustum(x1, x2, y1, y2, distanceToNearClippingPlane, distanceToFarClippingPlane);
    }

    private double[][] getFrustum(final double left, final double right, final double bottom, final double top, final double near, final double far) {
        return new double[][]{
                {2 * near / (right - left), 0.0f, 0.0f, 0.0f},
                {0.0f, 2 * near / (top - bottom), 0.0f, 0.0f},
                {(right + left) / (right - left), (top + bottom) / (top - bottom), (near + far) / (near - far), -1.0f},
                {0.0f, 0.0f, 2 * near * far / (near - far), 0.0f}
        };
    }
}
