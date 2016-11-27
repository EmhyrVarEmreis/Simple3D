package xyz.morecraft.dev.simple3d.engine.tool;

public class Settings {

    private Point cameraPosition;
    private Point lightSource;

    private double Ia = 1; // 4
    private double Ip = 1; // 5
    private double ka = 0.1; // 1
    private double kd = 0.5; // 2
    private double ks = 0.5; // 3
    private double n = 1; // 6
    private double c = 2; // 7

    private double lightAddon = 0.017;

    public Settings(Point cameraPosition, Point lightSource) {
        this.cameraPosition = cameraPosition;
        this.lightSource = lightSource;
    }

    public Settings() {
        this(new Point(0, 0, 0), new Point(0, 0, 0));
    }

    public Point getCameraPosition() {
        return cameraPosition;
    }

    public void setCameraPosition(Point cameraPosition) {
        this.cameraPosition = cameraPosition;
    }

    public Point getLightSourcePosition() {
        return lightSource;
    }

    public void setLightSource(Point lightSource) {
        this.lightSource = lightSource;
    }

    public double getIa() {
        return Ia;
    }

    public void setIa(double ia) {
        Ia = ia;
    }

    public double getIp() {
        return Ip;
    }

    public void setIp(double ip) {
        Ip = ip;
    }

    public double getKa() {
        return ka;
    }

    public void setKa(double ka) {
        this.ka = ka;
    }

    public double getKd() {
        return kd;
    }

    public void setKd(double kd) {
        this.kd = kd;
    }

    public double getKs() {
        return ks;
    }

    public void setKs(double ks) {
        this.ks = ks;
    }

    public double getN() {
        return n;
    }

    public void setN(double n) {
        this.n = n;
    }

    public double getC() {
        return c;
    }

    public void setC(double c) {
        this.c = c;
    }

    public double getLightAddon() {
        return lightAddon;
    }

    public void setLightAddon(double lightAddon) {
        this.lightAddon = lightAddon;
    }

    @Override
    public String toString() {
        return "Settings{" +
                "cameraPosition=" + cameraPosition +
                ", lightSource=" + lightSource +
                ", Ia=" + Ia +
                ", Ip=" + Ip +
                ", ka=" + ka +
                ", kd=" + kd +
                ", ks=" + ks +
                ", n=" + n +
                ", c=" + c +
                '}';
    }

}
