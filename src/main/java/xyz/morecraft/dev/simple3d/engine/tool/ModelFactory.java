package xyz.morecraft.dev.simple3d.engine.tool;

public final class ModelFactory {

    private static volatile int nextUid = 100;

    public static Model createCube(Point position, double size) {
        Model model = new Model(nextUid, "Cube" + nextUid, position);

        Vertex v1 = new Vertex(position);
        Vertex v2 = new Vertex(new Point(position.getX() + size, position.getY(), position.getZ()));
        Vertex v3 = new Vertex(new Point(position.getX(), position.getY() + size, position.getZ()));
        Vertex v4 = new Vertex(new Point(position.getX() + size, position.getY() + size, position.getZ()));
        Vertex v5 = new Vertex(new Point(position.getX(), position.getY(), position.getZ() + size));
        Vertex v6 = new Vertex(new Point(position.getX() + size, position.getY(), position.getZ() + size));
        Vertex v7 = new Vertex(new Point(position.getX(), position.getY() + size, position.getZ() + size));
        Vertex v8 = new Vertex(new Point(position.getX() + size, position.getY() + size, position.getZ() + size));

        model.addNewPolygon(Polygon.from(v1, v2, v4, v3));
        model.addNewPolygon(Polygon.from(v5, v6, v8, v7));
        model.addNewPolygon(Polygon.from(v1, v2, v6, v5));
        model.addNewPolygon(Polygon.from(v3, v4, v8, v7));
        model.addNewPolygon(Polygon.from(v1, v3, v7, v5));
        model.addNewPolygon(Polygon.from(v2, v4, v8, v6));

        nextUid++;

        return model;
    }

    public static Model createLine(Point p1, Point p2) {
        Model model = new Model(nextUid, "Cube" + nextUid, p1);

        Vertex v1 = new Vertex(p1);
        Vertex v2 = new Vertex(p2);

        model.addNewPolygon(Polygon.from(v1, v2));

        nextUid++;

        return model;
    }

    public static Model createHorizontalSquareMesh(Point p1, Point p2, double step) {
        Model model = new Model(nextUid, "Mesh" + nextUid, p1);

        for (double a = p1.getX(); a <= p2.getX(); a += step) {
            model.addNewPolygon(Polygon.from(new Vertex(new Point(a, p1.getY(), p1.getZ())), new Vertex(new Point(a, p2.getY(), p2.getZ()))));
        }

        for (double a = p1.getZ(); a <= p2.getZ(); a += step) {
            model.addNewPolygon(Polygon.from(new Vertex(new Point(p1.getX(), p1.getY(), a)), new Vertex(new Point(p2.getX(), p2.getY(), a))));
        }

        nextUid++;

        return model;
    }
}
