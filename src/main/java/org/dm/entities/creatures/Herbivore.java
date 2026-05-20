package org.dm.entities.creatures;

import org.dm.MapSimulation;
import org.dm.Position;
import org.dm.entities.Entity;
import org.dm.entities.static_object.Grass;

public class Herbivore extends Creature {

    public Herbivore(int speed, int health) {
        super(2, 20);
    }

    @Override
    protected void interactWithTarget(MapSimulation map, Position currentPosition, Position newPosition, Entity entity) {
        Grass grass = (Grass) entity;

        this.heal(grass.getNutritionValue());

        map.remove(currentPosition);
        map.add(newPosition,this );
    }

    @Override
    protected Class<? extends Entity> getTargetType() {
        return Grass.class;
    }
}
