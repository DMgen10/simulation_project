package org.dm.actions;

import org.dm.MapSimulation;
import org.dm.Position;
import org.dm.entities.Entity;
import org.dm.entities.creatures.Creature;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class HungryAction implements Action{

    private final MapSimulation map;

    public HungryAction(MapSimulation map) {
        this.map = map;
    }

    @Override
    public void execute(MapSimulation map) {

        List<Position> deadPos = new ArrayList<>();

        for (Map.Entry<Position, Entity> entry: map.getMap().entrySet()){
            Position position = entry.getKey();
            Entity entity = entry.getValue();

            if (entity instanceof Creature creature){
                creature.takeDamage(1);

                if (creature.isDead()){
                    deadPos.add(position);
                }
            }
        }
        for (Position position: deadPos){
            map.remove(position);
        }

//        Map.Entry<Position, Entity> positionEntityEntry = map.getMap().values();
//       for (Map.Entry<Position, Entity> entry: map.getMap().entrySet()){
//           Position position = entry.getKey();
//           Entity entity = entry.getValue();
//           if (entity instanceof Creature){
//               ((Creature) entity).takeDamage(1);
//               if (((Creature) entity).isDead()){
//                   map.remove(position);
//               }
//           }
//       }
    }
}
