package xyz.morecraft.dev.simple3d.engine.tool;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class World {

    private Map<Point, Model> modelMap;

    public World() {
        this(new HashMap<>());
    }

    public Model addModel(Model model) {
        return modelMap.put(model.getPosition(), model);
    }

    public World(Map<Point, Model> modelMap) {
        this.modelMap = modelMap;
    }

    public Collection<Model> getModelList() {
        return modelMap.values();
    }

    public Map<Point, Model> getModelMap() {
        return modelMap;
    }

    public void setModelMap(Map<Point, Model> modelMap) {
        this.modelMap = modelMap;
    }

}
