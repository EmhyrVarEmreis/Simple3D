/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package xyz.morecraft.dev.simple3d.engine.tool;


public class Mesh {

    private int wysokosc;
    private int szerokosc;

    private Point lewyDolnyRog;
    private Point prawyGornyRog;

    public Mesh(int wysokosc, int szerokosc, Point lewyDolnyRog, Point prawyGornyRog) {
        this.wysokosc = wysokosc;
        this.szerokosc = szerokosc;
        this.lewyDolnyRog = lewyDolnyRog;
        this.prawyGornyRog = prawyGornyRog;
    }

    public Point[][] createMeshPoints() {
        Point siatka[][] = new Point[wysokosc][szerokosc];
        double dx = (prawyGornyRog.getX() - lewyDolnyRog.getX()) / szerokosc;
        double dy = (prawyGornyRog.getY() - lewyDolnyRog.getY()) / wysokosc;
        //zakladam ze wsp z rogow siatki sa takie same
        if (prawyGornyRog.getZ() != lewyDolnyRog.getZ()) {
            try {
                throw new Exception("Wspolrzedne z krancow siatki sa rozne");
            } catch (Exception ignored) {

            }
        }

        for (int y = 0; y < wysokosc; y++) {
            for (int x = 0; x < szerokosc; x++) {
                Point p = new Point(lewyDolnyRog.getX() + x * dx, lewyDolnyRog.getY() + y * dy, lewyDolnyRog.getZ());
                siatka[y][x] = p;
            }
        }
        return siatka;
    }


}
