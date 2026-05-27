package org.dm.simulation.actions;

import org.dm.simulation.model.MapSimulation;
import org.dm.simulation.model.Position;
import org.dm.simulation.entities.Entity;
import org.dm.simulation.entities.creatures.Creature;
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

    }
}
