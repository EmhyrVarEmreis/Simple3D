package xyz.morecraft.dev.simple3d.engine.tool;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class World {

    private Map<Point, PhongSphere> phongSphereMap;

    public World() {
        this(new HashMap<>());
    }

    public PhongSphere addPhongSphere(PhongSphere phongSphere) {
        return phongSphereMap.put(phongSphere.getPosition(), phongSphere);
    }

    public World(Map<Point, PhongSphere> phongSphereMap) {
        this.phongSphereMap = phongSphereMap;
    }

    public Collection<PhongSphere> getPhongSpherelList() {
        return phongSphereMap.values();
    }

    public Map<Point, PhongSphere> getPhongSphereMap() {
        return phongSphereMap;
    }

    public void setPhongSphereMap(Map<Point, PhongSphere> phongSphereMap) {
        this.phongSphereMap = phongSphereMap;
    }

}
