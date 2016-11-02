package xyz.morecraft.dev.simple3d.engine.tool;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Model extends Entity {

    private List<Polygon> polygonList;
    private Point middlePoint;

    public Model(int id, String name, Point position) {
        this(id, name, position, new ArrayList<>());
    }

    public Model(int id, String name, Point position, List<Polygon> polygonList) {
        super(id, name, position);
        this.polygonList = polygonList;
        recalculateMiddlePoint();
    }

    public boolean addNewPolygon(Polygon polygon) {
        boolean b = polygonList.add(polygon);
        recalculateMiddlePoint();
        return b;
    }

    public Collection<Polygon> getPolygonList() {
        return polygonList;
    }

    public void setPolygonList(List<Polygon> polygonList) {
        this.polygonList = polygonList;
        recalculateMiddlePoint();
    }

    @Override
    public String toString() {
        return super.toString();
    }

    public Point getMiddlePoint() {
        if (middlePoint == null) {
            recalculateMiddlePoint();
        }
        return middlePoint;
    }

    private void recalculateMiddlePoint() {
        final double[] d = new double[]{0.0, 0.0, 0.0};
        polygonList.forEach(
                p -> {
                    d[0] += p.getMiddlePoint().getX();
                    d[1] += p.getMiddlePoint().getY();
                    d[2] += p.getMiddlePoint().getZ();
                }
        );
        middlePoint = new Point(d[0] / polygonList.size(), d[1] / polygonList.size(), d[2] / polygonList.size());
    }

}