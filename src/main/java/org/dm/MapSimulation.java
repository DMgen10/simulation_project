package org.dm;

import org.dm.entities.Entity;

import java.util.HashMap;
import java.util.Map;

public class MapSimulation {

    private int widthMap;
    private int heightMap;
    private Map<Position, Entity> map;

    public MapSimulation(int width, int height){

        if (width < 0 || height < 0){
            throw new IllegalArgumentException("the width and height cannot be less than zero");
        }

        widthMap = width;
        heightMap = height;
        map = new HashMap<>(widthMap * heightMap);
    }

    public Entity getEntity(Position position){
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

    public void add(Position position, Entity entity){
        map.put(position, entity);
    }

    public void remove(Position position){
        map.remove(position);
    }

    public boolean isBusy(Position position){
        return map.containsKey(position);
    }

    public boolean isAcceptableLimit(int x, int y) {
//        return !(position.x() > getWidth() || position.y() > getHeight() || position.x() < 0 || position.y() < 0);
        return x >= 0 && x < getHeight() && y >= 0 && getHeight() > y;
    }

    public void showCountCreatures(){
        int countPredators = 0;
        int countHerbivores = 0;
        int countGrass = 0;

        for (Entity count: map.values()){
            if (count instanceof Herbivore){
                countHerbivores++;
            }
            if (count instanceof Predator){
                countPredators++;
            }
            if (count instanceof Grass){
                countGrass++;
            }
        }

        System.out.println("Хищников: " + countPredators);
        System.out.println("Травоядных: " + countHerbivores);
        System.out.printf("Травы: " + countGrass);
    }

    /*
        1. Метод добавления сущности по координате и указанию существа
        2. Метод удаления по координате
        3. Метод проверки, занята ли позиция
        4. Метод проверки, находится ли позиция в пределах карты
     */
}
