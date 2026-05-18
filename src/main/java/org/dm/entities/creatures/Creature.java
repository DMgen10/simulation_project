package org.dm.entities.creatures;

import org.dm.MapSimulation;
import org.dm.Position;
import org.dm.entities.Entity;

public class Creature extends Entity {

    private int speed;
    private int health;

    // в процессе реализации
    public void makeMove(MapSimulation map, Position currentPosition){
        map.remove(currentPosition);
        map.add(currentPosition, map.getEntity(currentPosition));
    }


}
