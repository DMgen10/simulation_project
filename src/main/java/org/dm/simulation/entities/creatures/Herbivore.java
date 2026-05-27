package org.dm.simulation.entities.creatures;

import org.dm.simulation.model.MapSimulation;
import org.dm.simulation.model.Position;
import org.dm.simulation.entities.Entity;
import org.dm.simulation.entities.static_object.Grass;

public class Herbivore extends Creature {

    public Herbivore(int speed, int health) {
        super(speed,health);
    }

    @Override
    protected void interactWithTarget(MapSimulation map, Position currentPosition, Position newPosition, Entity targetEntity) {
        Grass grass = (Grass) targetEntity;

        this.heal(grass.getNutritionValue());

        map.remove(currentPosition);
        map.add(newPosition,this );
    }

    @Override
    protected Class<? extends Entity> getTargetType() {
        return Grass.class;
    }

    @Override
    public Creature createChild() {
        return new Herbivore(this.getSpeed(), getHealth());
    }
}
