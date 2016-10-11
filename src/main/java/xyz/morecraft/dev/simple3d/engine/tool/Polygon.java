package xyz.morecraft.dev.simple3d.engine.tool;

import java.util.ArrayList;
import java.util.List;

public class Polygon {

    private List<Vertex> sortedVertexList;

    public Polygon() {
        this(new ArrayList<>());
    }

    public Polygon(List<Vertex> sortedVertexList) {
        this.sortedVertexList = sortedVertexList;
    }

    public boolean addNextVertex(Vertex vertex) {
        return sortedVertexList.add(vertex);
    }

    public List<Vertex> getSortedVertexList() {
        return sortedVertexList;
    }

    public void setSortedVertexList(List<Vertex> sortedVertexList) {
        this.sortedVertexList = sortedVertexList;
    }

    public static Polygon from(Vertex... sortedVertices) {
        Polygon polygon = new Polygon();
        for (Vertex vertex : sortedVertices) {
            polygon.addNextVertex(vertex);
        }
        return polygon;
    }

}
