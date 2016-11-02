package xyz.morecraft.dev.simple3d.engine.tool;

public class Vertex {

    private Point position;

    public Vertex(Point position) {
        this.position = position;
    }

    public Point getPosition() {
        return position;
    }

    public void setPosition(Point position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return "Vertex{" +
                "position=" + position +
                '}';
    }

}
