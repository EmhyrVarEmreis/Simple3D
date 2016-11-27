package xyz.morecraft.dev.simple3d.engine;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import xyz.morecraft.dev.simple3d.configuration.WindowConfiguration;
import xyz.morecraft.dev.simple3d.engine.tool.*;
import xyz.morecraft.dev.simple3d.engine.tool.Point;
import xyz.morecraft.dev.simple3d.main.ImageBean;

import java.awt.*;
import java.awt.image.BufferedImage;

@Component
public final class Screen {

    private static final Logger log = LoggerFactory.getLogger(Screen.class);

    private static final Color INFO_BACKGROUND = new Color(.1f, .1f, .1f, .80f);

    private final WindowConfiguration windowConfiguration;
    private final BufferedImage bufferedImage;
    private final Settings settings;
    private final World world;

    private Graphics2D g2d;
    private Point[][] meshPoints;

    @Autowired
    public Screen(WindowConfiguration windowConfiguration, Settings settings, ImageBean imageBean, World world) {
        this.windowConfiguration = windowConfiguration;
        this.settings = settings;
        this.world = world;

        this.g2d = imageBean.getImage().createGraphics();
        this.bufferedImage = imageBean.getImage();

        this.g2d.setFont(new Font("Arial", Font.PLAIN, 9));

        this.meshPoints = new Mesh(windowConfiguration.getHeight(), windowConfiguration.getWidth(), new Point(-1, -1, 1), new Point(1, 1, 1)).createMeshPoints();
    }

    public void update() {
        fillScreen();
        drawObjects();
        drawInfo();
    }

    private void drawObjects() {
        world.getPhongSpherelList().forEach(
                this::drawPhongSphere
        );
    }

    @SuppressWarnings("Duplicates")
    private void drawPhongSphere(PhongSphere phongSphere) {
        Double intensityMax = Double.MIN_VALUE;
        Double intensityMin = Double.MAX_VALUE;

        Double[][] intensityBuffer = new Double[windowConfiguration.getWidth()][windowConfiguration.getHeight()];

        for (int x = 0; x < windowConfiguration.getWidth(); x++) {
            for (int y = 0; y < windowConfiguration.getHeight(); y++) {
                Double intensity = getLightning(phongSphere.getPosition(), phongSphere.intersection(settings.getCameraPosition(), meshPoints[y][x]));
                if (intensity != null) {
                    if (intensity > intensityMax) {
                        intensityMax = intensity;
                    }
                    if (intensity < intensityMin) {
                        intensityMin = intensity;
                    }

                }
                intensityBuffer[x][y] = intensity;
            }
        }
        for (int x = 0; x < windowConfiguration.getWidth(); x++) {
            for (int y = 0; y < windowConfiguration.getHeight(); y++) {
                Double intensity = intensityBuffer[x][y];
                if (intensity != null) {
                    double f = intensity / (intensityMax + settings.getLightAddon());
                    bufferedImage.setRGB(
                            x,
                            y,
                            new Color((int) (f * 255 + 0.5), (int) (f * 255 + 0.5), (int) (f * 255 + 0.5)).getRGB()
                    );
                }
            }
        }
    }

    private Double getLightning(Point spherePosition, Point pointOnSphere) {
        if (pointOnSphere == null) {
            return null;
        }

        Vector n = new Vector(spherePosition, pointOnSphere).normalize();
        Vector v = new Vector(pointOnSphere, settings.getCameraPosition()).normalize();
        Vector l = new Vector(pointOnSphere, settings.getLightSource());
        double rr = l.norma();
        l = l.normalize();
        Vector r = (n.mul(2)).mul(n.dotProduct(l)).dif(l).normalize();
        return settings.getIa() * settings.getKa() + settings.getIp() * (settings.getKd() * Math.max(n.dotProduct(l), 0) + settings.getKs() * Math.pow(Math.max(r.dotProduct(v), 0), settings.getN())) / (settings.getC() + rr);
    }

    private void fillScreen() {
        g2d.setColor(Color.BLACK);
        g2d.fillRect(0, 0, windowConfiguration.getWidth(), windowConfiguration.getHeight());
    }

    private void drawInfo() {
        g2d.setColor(INFO_BACKGROUND);
        g2d.fillRect(0, 0, 125, 150);
        g2d.setColor(Color.RED);
        g2d.drawString("Camera:", 5, 35);
        g2d.drawString("x = " + settings.getCameraPosition().getX(), 10, 45);
        g2d.drawString("y = " + settings.getCameraPosition().getY(), 10, 55);
        g2d.drawString("z = " + settings.getCameraPosition().getZ(), 10, 65);
        g2d.drawString("PhongSphere:", 5, 75);
        g2d.drawString("Ia = " + settings.getIa(), 10, 85);
        g2d.drawString("Ip = " + settings.getIp(), 10, 95);
        g2d.drawString("ka = " + settings.getKa(), 10, 105);
        g2d.drawString("kd = " + settings.getKd(), 10, 115);
        g2d.drawString("ks = " + settings.getKs(), 10, 125);
        g2d.drawString("n = " + settings.getN(), 10, 135);
        g2d.drawString("c = " + settings.getC(), 10, 145);
    }

}
