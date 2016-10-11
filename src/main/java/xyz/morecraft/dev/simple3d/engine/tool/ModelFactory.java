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
        model.addNewPolygon(Polygon.from(v5, v5, v8, v7));
        model.addNewPolygon(Polygon.from(v1, v2, v6, v5));
        model.addNewPolygon(Polygon.from(v3, v4, v8, v7));
        model.addNewPolygon(Polygon.from(v1, v3, v7, v5));
        model.addNewPolygon(Polygon.from(v2, v4, v8, v6));

        nextUid++;

        return model;
    }

}
