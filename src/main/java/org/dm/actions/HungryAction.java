package org.dm.actions;

import org.dm.MapSimulation;
import org.dm.Position;
import org.dm.entities.Entity;
import org.dm.entities.creatures.Creature;

import java.util.Map;


public class HungryAction implements Action{

    private final MapSimulation map;

    public HungryAction(MapSimulation map) {
        this.map = map;
    }

    @Override
    public void execute(MapSimulation map) {
       for (Map.Entry<Position, Entity> entry: map.getMap().entrySet()){
           Position position = entry.getKey();
           Entity entity = entry.getValue();
           if (entity instanceof Creature){
               ((Creature) entity).takeDamage(1);
               if (((Creature) entity).isDead()){
                   ((Creature) entity).remove(map,position);
               }
           }
       }


    }
}
