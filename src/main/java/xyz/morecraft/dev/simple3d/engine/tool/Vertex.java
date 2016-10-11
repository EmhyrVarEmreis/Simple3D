package xyz.morecraft.dev.simple3d.engine.tool;

import java.awt.*;

public class Vertex {

    private Point position;
    private Color color;

    public Vertex(Point position) {
        this(position, Color.BLACK);
    }

    public Vertex(Point position, Color color) {
        this.position = position;
        this.color = color;
    }

    public Point getPosition() {
        return position;
    }

    public void setPosition(Point position) {
        this.position = position;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Vertex{" +
                "position=" + position +
                ", color=" + color +
                '}';
    }

}
