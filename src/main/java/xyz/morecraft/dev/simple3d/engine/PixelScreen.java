package xyz.morecraft.dev.simple3d.engine;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import xyz.morecraft.dev.simple3d.configuration.EngineConfiguration;
import xyz.morecraft.dev.simple3d.engine.tool.*;
import xyz.morecraft.dev.simple3d.engine.tool.Point;
import xyz.morecraft.dev.simple3d.engine.tool.Polygon;
import xyz.morecraft.dev.simple3d.main.Window;
import xyz.morecraft.dev.simple3d.projection.Projection;

import java.awt.*;

public class PixelScreen extends Screen {

    private static final Logger log = LoggerFactory.getLogger(PixelScreen.class);

    private int[] pixels;

    public PixelScreen(EngineConfiguration configuration, Projection projection, Camera camera, Window window, World world) {
        super(configuration, projection, camera, window, world);
        this.pixels = window.getPixels();
    }

    public void update() {
        int n = pixels.length;

        for (int i = 0; i < pixels.length; i++) {
            pixels[i] = Color.GRAY.getRGB();
        }

        getProjection().initMatrix(-20, 20, -20, 20, 1, 20);

        getWorld().getModelList().forEach(
                model -> drawModel(pixels, model)
        );

//        drawLine(pixels, 50, 50, 150, 150);

//        int pos = n / 2 + ((int) camera.getPosition().getX()) - (((int) camera.getPosition().getZ()) * configuration.getWidth()) + configuration.getWidth() / 2;
//
//        if (pos >= 0 && pos < n) {
//            pixels[pos] = Color.RED.getRGB();
//        }

        log.info("");
    }

    private int[] drawModel(int[] pixels, Model model) {
        log.debug("Drawing model {}", model);
        model.getPolygonList().forEach(
                polygon ->
                        drawPolygon(pixels, polygon)
        );
        return pixels;
    }

    private int[] drawPolygon(int[] pixels, Polygon polygon) {
        log.debug("Drawing polygon");
        CalculatedPoint last = null;
        for (Vertex vertex : polygon.getSortedVertexList()) {
            CalculatedPoint calculated = getProjection().calculatePoint(vertex.getPosition());
            if (last != null) {
                drawLine(pixels, last, calculated);
            }
            last = calculated;
        }
        if (polygon.getSortedVertexList().size() > 2) {
            drawLine(pixels, last, getProjection().calculatePoint(polygon.getSortedVertexList().get(0).getPosition()));
        }
        return pixels;
    }

    private int[] drawLine(int[] pixels, CalculatedPoint p1, CalculatedPoint p2) {
        return drawLine(pixels, p1.getX(), p1.getY(), p2.getX(), p2.getY());
    }

    private int[] drawLine(int[] pixels, Point p1, Point p2) {
        return drawLine(pixels, (int) p1.getX(), (int) p1.getY(), (int) p2.getX(), (int) p2.getY());
    }

    private int[] drawLine(int[] pixels, int x1, int y1, int x2, int y2) {
        log.debug("Drawing line between {},{} {},{}", x1, y1, x2, y2);
        if (x1 > x2) {
            int tmp = x1;
            x1 = x2;
            x2 = tmp;
            tmp = y1;
            y1 = y2;
            y2 = tmp;
        }
        pixels[getPos(x1, y1)] = Color.BLACK.getRGB();
        pixels[getPos(x2, y2)] = Color.BLACK.getRGB();
        for (int x = x1; x < x2; x++) {
            pixels[getPos(x, ((y2 - y1) * (1.0 * x - x1) / (x2 - x1)) + y1)] = Color.BLACK.getRGB();
        }
        return pixels;
    }

    private int getPos(Point point) {
        return getPos(point.getX(), point.getY());
    }

    private int getPos(double x, double y) {
        return getPos((int) Math.round(x), (int) Math.round(y));
    }

    private int getPos(int x, int y) {
        return Math.abs(x * getConfiguration().getWidth() + y);
    }

}
