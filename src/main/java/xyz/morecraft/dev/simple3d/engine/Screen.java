package xyz.morecraft.dev.simple3d.engine;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import xyz.morecraft.dev.simple3d.configuration.WindowConfiguration;
import xyz.morecraft.dev.simple3d.engine.projection.Projection;
import xyz.morecraft.dev.simple3d.engine.tool.*;
import xyz.morecraft.dev.simple3d.engine.tool.Polygon;
import xyz.morecraft.dev.simple3d.main.Window;

import java.awt.*;
import java.util.PriorityQueue;

@Component
public final class Screen {

    private static final Logger log = LoggerFactory.getLogger(Screen.class);

    private static final Color INFO_BACKGROUND = new Color(.1f, .1f, .1f, .80f);

    private Graphics2D g2d;

    private final WindowConfiguration windowConfiguration;
    private final Projection projection;
    private final Camera camera;
    private final World world;

    private final PriorityQueue<ObjectWrapper<Model>> modelQueue = new PriorityQueue<>();
    private final PriorityQueue<ObjectWrapper<Polygon>> polygonQueue = new PriorityQueue<>();

    @Autowired
    public Screen(WindowConfiguration windowConfiguration, Projection projection, Camera camera, Window window, World world) {
        this.windowConfiguration = windowConfiguration;
        this.projection = projection;
        this.camera = camera;
        this.world = world;
        this.g2d = window.getImage().createGraphics();

        this.g2d.setFont(new Font("Arial", Font.PLAIN, 9));
    }

    public void recalculate() {
        projection.recalculate();
    }

    public void update() {
        fillScreen();
        drawObjects();
        drawInfo();
    }

    private void drawObjects() {
        for (Model model : world.getModelList()) {
            if (model.getPolygonList().size() > 25) { // FIX FOR MESH
                modelQueue.offer(
                        new ObjectWrapper<>(
                                model,
                                Double.MAX_VALUE
                        )
                );
            } else {
                modelQueue.offer(
                        new ObjectWrapper<>(
                                model,
                                projection.calculatePoint(model.getMiddlePoint()).getW()
                        )
                );
            }
        }
        while (!modelQueue.isEmpty()) {
            drawModel(modelQueue.poll().o);
        }
    }

    private void fillScreen() {
        g2d.setColor(Color.BLACK);
        g2d.fillRect(0, 0, windowConfiguration.getWidth(), windowConfiguration.getHeight());
    }

    private void drawInfo() {
        g2d.setColor(INFO_BACKGROUND);
        g2d.fillRect(0, 0, 125, 110);
        g2d.setColor(Color.RED);
        g2d.drawString("Camera", 5, 35);
        g2d.drawString("x = " + camera.getPosition().getX(), 10, 45);
        g2d.drawString("y = " + camera.getPosition().getY(), 10, 55);
        g2d.drawString("z = " + camera.getPosition().getZ(), 10, 65);
        g2d.drawString("θx = " + camera.getAngleX(), 10, 75);
        g2d.drawString("θy = " + camera.getAngleY(), 10, 85);
        g2d.drawString("θz = " + camera.getAngleZ(), 10, 95);
        g2d.drawString("zoom = " + camera.getZoom(), 10, 105);
    }

    private void drawModel(Model model) {
        int shadeLevel = model.getPolygonList().size() > 25 ? 0 : model.getPolygonList().size();
        for (Polygon polygon : model.getPolygonList()) {
            polygonQueue.offer(
                    new ObjectWrapper<>(
                            polygon,
                            projection.calculatePoint(polygon.getMiddlePoint()).getW()
                    )
            );
        }
        while (!polygonQueue.isEmpty()) {
            drawPolygon(polygonQueue.poll().o, --shadeLevel);
        }
    }

    private void drawPolygon(Polygon polygon, int shadeLevel) {
        int[] xP = new int[polygon.getSortedVertexList().size()];
        int[] yP = new int[polygon.getSortedVertexList().size()];
        int n = 0;

        for (Vertex vertex : polygon.getSortedVertexList()) {
            CalculatedPoint cp = projection.calculatePoint(vertex.getPosition());
            xP[n] = cp.getX();
            yP[n] = cp.getY();
            n++;
        }

        int rgb = polygon.getColor().getRGB();
        while (shadeLevel-- >= 0) {
            rgb = ((rgb & 0xfefefefe) >> 1);
        }
        g2d.setColor(new Color(rgb));

        if (n == 1) {
            g2d.drawLine(xP[0], yP[0], xP[0], yP[0]);
        } else if (n == 2) {
            g2d.drawLine(xP[0], yP[0], xP[1], yP[1]);
        } else {
            g2d.fillPolygon(xP, yP, n);
        }

    }

    private void drawLine(int x1, int y1, int x2, int y2) {
        g2d.drawLine(x1, y1, x2, y2);
    }

    private class ObjectWrapper<T> implements Comparable<ObjectWrapper<T>> {
        private T o;
        private double w;

        public ObjectWrapper(T o, double w) {
            this.o = o;
            this.w = w;
        }

        @Override
        public int compareTo(ObjectWrapper<T> o) {
            return Double.compare(w, o.w) * -1;
        }
    }

}
