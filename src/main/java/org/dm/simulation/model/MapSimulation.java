package org.dm.simulation.model;

import org.dm.simulation.entities.Entity;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class MapSimulation {

    private final int widthMap;
    private final int heightMap;
    private final Map<Position, Entity> map;
    private final Random random = new Random();

    public MapSimulation(int width, int height) {

        if (width < 0 || height < 0) {
            throw new IllegalArgumentException("the width and height cannot be less than zero");
        }

        widthMap = width;
        heightMap = height;
        map = new HashMap<>(widthMap * heightMap);
    }

    public Entity getEntity(Position position) {
        return map.get(position);
    }

    public Map<Position, Entity> getMap() {
        return map;
    }

    public int getWidth() {
        return widthMap;
    }

    public int getHeight() {
        return heightMap;
    }

    public void add(Position position, Entity entity) {
        map.put(position, entity);
    }

    public void remove(Position position) {
        map.remove(position);
    }

    public boolean isBusy(Position position) {
        return map.containsKey(position);
    }

    public boolean isAcceptableLimit(int x, int y) {
        return x >= 0 && x < getHeight() && y >= 0 && getHeight() > y;
    }

    public Position getRandomPosition(){

        while (true){
            int x = random.nextInt(0,getWidth());
            int y = random.nextInt(0,getHeight());
            Position position = new Position(x,y);
            if (!isBusy(position)){
                return position;
            }
        }
    }
}
