package xyz.morecraft.dev.simple3d.engine.tool;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Polygon {

    private List<Vertex> sortedVertexList;
    private Color color = Color.GREEN;
    private Point middlePoint;

    public Polygon() {
        this(new ArrayList<>());
    }

    public Polygon(List<Vertex> sortedVertexList) {
        this.sortedVertexList = sortedVertexList;
        recalculateMiddlePoint();
    }

    public boolean addNextVertex(Vertex vertex) {
        boolean b = sortedVertexList.add(vertex);
        recalculateMiddlePoint();
        return b;
    }

    public List<Vertex> getSortedVertexList() {
        return sortedVertexList;
    }

    public void setSortedVertexList(List<Vertex> sortedVertexList) {
        this.sortedVertexList = sortedVertexList;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public static Polygon from(Vertex... sortedVertices) {
        Polygon polygon = new Polygon();
        for (Vertex vertex : sortedVertices) {
            polygon.addNextVertex(vertex);
        }
        polygon.recalculateMiddlePoint();
        return polygon;
    }

    public Point getMiddlePoint() {
        if (middlePoint == null) {
            recalculateMiddlePoint();
        }
        return middlePoint;
    }

    private void recalculateMiddlePoint() {
        final double[] d = new double[]{0.0, 0.0, 0.0};
        sortedVertexList.forEach(
                v -> {
                    d[0] += v.getPosition().getX();
                    d[1] += v.getPosition().getY();
                    d[2] += v.getPosition().getZ();
                }
        );
        middlePoint = new Point(d[0] / sortedVertexList.size(), d[1] / sortedVertexList.size(), d[2] / sortedVertexList.size());
    }

}
