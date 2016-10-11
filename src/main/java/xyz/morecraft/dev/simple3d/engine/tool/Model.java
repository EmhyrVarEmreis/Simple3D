package xyz.morecraft.dev.simple3d.engine.tool;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Model extends Entity {

    private List<Polygon> polygonList;

    public Model(int id, String name, Point position) {
        this(id, name, position, new ArrayList<>());
    }

    public Model(int id, String name, Point position, List<Polygon> polygonList) {
        super(id, name, position);
        this.polygonList = polygonList;
    }

    public boolean addNewPolygon(Polygon polygon) {
        return polygonList.add(polygon);
    }

    public Collection<Polygon> getPolygonList() {
        return polygonList;
    }

    public void setPolygonList(List<Polygon> polygonList) {
        this.polygonList = polygonList;
    }

    @Override
    public String toString() {
        return super.toString();
    }

}