package xyz.morecraft.dev.simple3d.engine;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import xyz.morecraft.dev.simple3d.configuration.EngineConfiguration;
import xyz.morecraft.dev.simple3d.engine.tool.*;
import xyz.morecraft.dev.simple3d.engine.tool.Polygon;
import xyz.morecraft.dev.simple3d.main.Window;
import xyz.morecraft.dev.simple3d.projection.Projection;

import java.awt.*;

public class DrawingScreen extends Screen {

    private static final Logger log = LoggerFactory.getLogger(DrawingScreen.class);

    private Graphics2D g2d;

    public DrawingScreen(EngineConfiguration configuration, Projection projection, Camera camera, Window window, World world) {
        super(configuration, projection, camera, window, world);
        this.g2d = window.getImage().createGraphics();
    }

    public void update() {
        getProjection().initMatrix(-20, 20, -20, 20, 1, 20);

        log.debug(getCamera().getPosition().toString());

        g2d.setColor(Color.BLACK);
        g2d.fillRect(0, 0, getConfiguration().getWidth(), getConfiguration().getHeight());

        g2d.setColor(Color.WHITE);

        getWorld().getModelList().forEach(
                this::drawModel
        );

        //log.info("");
    }

    private void drawModel(Model model) {
        //log.debug("Drawing model {}", model);
        model.getPolygonList().forEach(
                this::drawPolygon
        );
    }

    private void drawPolygon(Polygon polygon) {
        //log.debug("Drawing polygon");
        CalculatedPoint last = null;
        CalculatedPoint calculated;
        for (Vertex vertex : polygon.getSortedVertexList()) {
            calculated = getProjection().calculatePoint(vertex.getPosition());
            if (last != null) {
                g2d.drawLine(last.getX(), last.getY(), calculated.getX(), calculated.getY());
            }
            last = calculated;
        }
        if (last != null && polygon.getSortedVertexList().size() > 2) {
            calculated = getProjection().calculatePoint(polygon.getSortedVertexList().get(0).getPosition());
            g2d.drawLine(last.getX(), last.getY(), calculated.getX(), calculated.getY());
        }
    }

}
