package org.dm.simulation.actions;

import org.dm.simulation.MapSimulation;
import org.dm.simulation.Position;
import org.dm.simulation.entities.Entity;
import org.dm.simulation.entities.creatures.Creature;

import java.util.HashMap;
import java.util.Map;

public class MoveAction implements Action {

    private final MapSimulation map;

    public MoveAction(MapSimulation map) {
        this.map = map;
    }

    @Override
    public void execute(MapSimulation map) {
        Map<Position, Creature> creaturesForTurn = countingCreatures(map);

        for (Map.Entry<Position,Creature> entry: creaturesForTurn.entrySet()){
            Position currentPosition = entry.getKey();
            Creature creature = entry.getValue();
            creature.makeMove(map, currentPosition);
        }
    }

    private Map<Position, Creature> countingCreatures(MapSimulation map){
        Map<Position, Creature> creatures = new HashMap<>();
        for (int x = 0; x < map.getWidth(); x++) {
            for (int y = 0; y < map.getHeight(); y++) {
                Position position = new Position(x,y);
                Entity entity = map.getEntity(position);

                if (map.getEntity(position) instanceof Creature && entity != null){
                    creatures.put(position, (Creature) entity);
                }
            }
        }
        return creatures;
    }
}
