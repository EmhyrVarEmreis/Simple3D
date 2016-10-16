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

@Component
public final class Screen {

    private static final Logger log = LoggerFactory.getLogger(Screen.class);

    private Graphics2D g2d;

    private final WindowConfiguration windowConfiguration;
    private final Projection projection;
    private final Camera camera;
    private final World world;

    @Autowired
    public Screen(WindowConfiguration windowConfiguration, Projection projection, Camera camera, Window window, World world) {
        this.windowConfiguration = windowConfiguration;
        this.projection = projection;
        this.camera = camera;
        this.world = world;
        this.g2d = window.getImage().createGraphics();

        this.g2d.setFont(new Font("Arial", Font.PLAIN, 9));
    }

    public void update() {

        fillScreen();
        drawInfo();

//        if (iii % 10 == 0) {
//            log.debug(new Point(Math.round(getCamera().getPosition().getX() * 100.0) / 100.0, Math.round(getCamera().getPosition().getY() * 100.0) / 100.0, Math.round(getCamera().getPosition().getZ() * 100.0) / 100.0).toString());
//        }
//        iii++;

        world.getModelList().forEach(
                this::drawModel
        );

        //log.info("");
    }

    private void fillScreen() {
        g2d.setColor(Color.BLACK);
        g2d.fillRect(0, 0, windowConfiguration.getWidth(), windowConfiguration.getHeight());
    }

    private void drawInfo() {
        g2d.setColor(Color.RED);
        this.g2d.setFont(new Font("Arial", Font.PLAIN, 9));
        g2d.drawString("Camera", 5, 35);
        g2d.drawString("x = " + camera.getPosition().getX(), 10, 45);
        g2d.drawString("y = " + camera.getPosition().getY(), 10, 55);
        g2d.drawString("z = " + camera.getPosition().getZ(), 10, 65);
        g2d.drawString("θx = " + camera.getAngleX(), 10, 75);
        g2d.drawString("θy = " + camera.getAngleY(), 10, 85);
        g2d.drawString("θz = " + camera.getAngleZ(), 10, 95);
    }

    private void drawModel(Model model) {
        //log.debug("Drawing model {}", model);
        if (model.getPolygonList().size() > 10) {
            g2d.setColor(Color.GREEN);
        } else {
            g2d.setColor(Color.BLUE);
        }
        model.getPolygonList().forEach(
                this::drawPolygon
        );
    }

    private void drawPolygon(Polygon polygon) {
        //log.debug("Drawing polygon");
        CalculatedPoint last = null;
        CalculatedPoint calculated;
        for (Vertex vertex : polygon.getSortedVertexList()) {
            calculated = projection.calculatePoint(vertex.getPosition());
            if (last != null) {
                drawLine(last.getX(), last.getY(), calculated.getX(), calculated.getY());
            }
            last = calculated;
        }
        if (last != null && polygon.getSortedVertexList().size() > 2) {
            calculated = projection.calculatePoint(polygon.getSortedVertexList().get(0).getPosition());
            drawLine(last.getX(), last.getY(), calculated.getX(), calculated.getY());
        }
    }

    private void drawLine(int x1, int y1, int x2, int y2) {
//        if (x1 <= 0 || x1 >= windowConfiguration.getWidth() || x2 <= 0 || x2 >= windowConfiguration.getWidth()
//                || y1 <= 0 || y1 >= windowConfiguration.getHeight() || y2 <= 0 || y2 >= windowConfiguration.getHeight()) {
//            log.warn("Drawing line [({}, {}); ({}, {})]", x1, y1, x2, y2);
//        }
        g2d.drawLine(x1, windowConfiguration.getHeight() - y1, x2, windowConfiguration.getHeight() - y2);
    }

}
