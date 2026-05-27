package org.dm.simulation.actions;

import org.dm.simulation.model.MapSimulation;
import org.dm.simulation.model.Position;
import org.dm.simulation.config.SettingsSimulation;
import org.dm.simulation.entities.Entity;
import org.dm.simulation.entities.creatures.Creature;

import java.util.*;

public class ActionReproduction implements Action{

    private final int REPRODUCTION_THRESHOLD;

    public ActionReproduction(MapSimulation map, SettingsSimulation settingsSimulation) {
        REPRODUCTION_THRESHOLD = (settingsSimulation.getHealthHerbivore() + settingsSimulation.getHealthPredator()) / 2;
    }

    @Override
    public void execute(MapSimulation map) {
        Set<Creature> already = new HashSet<>();

        int[][] directions = {{1,0},{-1,0},{0,1},{0,-1}};

        List<Position> positions = new ArrayList<>(map.getMap().keySet());

        for (Position currentPosition: positions){
            Entity entity = map.getEntity(currentPosition);

            if (entity instanceof Creature parent){

                if (parent.getHealth() >= REPRODUCTION_THRESHOLD && !already.contains(parent)){


                    Position partnerPosition = null;
                    Creature parentPartner = null;

                    for (int[] dir: directions){
                        int x = currentPosition.x() + dir[0];
                        int y = currentPosition.y() + dir[1];

                        if (map.isAcceptableLimit(x, y)){
                            Position neighborPosition = new Position(x,y);
                            Entity neighbor = map.getEntity(neighborPosition);

                            if (neighbor != null && neighbor.getClass() == parent.getClass()){
                                Creature potentialPartner = (Creature) neighbor;
                                if (potentialPartner.getHealth() >= REPRODUCTION_THRESHOLD && !already.contains(potentialPartner)){
                                    partnerPosition = neighborPosition;
                                    parentPartner = potentialPartner;
                                    break;
                                }
                            }
                        }
                    }
                    if (parentPartner != null){
                        Position birthPosition = findFreeNeighborPosition(map, currentPosition, directions);

                        if (birthPosition == null){
                            birthPosition = findFreeNeighborPosition(map, partnerPosition, directions);
                        }
                        if (birthPosition != null){
                            Creature child = parent.createChild();
                            map.add(birthPosition, child);

                            already.add(parent);
                            already.add(parentPartner);
                        }
                    }
                }
            }
        }
    }

    private Position findFreeNeighborPosition(MapSimulation map, Position currentPosition, int[][] directions) {
        for (int[] dir : directions){
            int x  = currentPosition.x() + dir[0];
            int y  = currentPosition.y() + dir[1];
            if (map.isAcceptableLimit(x,y)){
                Position position = new Position(x,y);
                if (!map.isBusy(position)){
                    return position;
                }
            }
        }
        return null;
    }


}
