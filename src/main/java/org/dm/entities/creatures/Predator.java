package org.dm.entities.creatures;

import org.dm.MapSimulation;
import org.dm.Position;
import org.dm.entities.Entity;
import org.dm.entities.static_object.Grass;

public class Predator extends Creature {

    private int damage;

    public int getDamage() {
        return damage;
    }

    public Predator(int speed, int health) {
        super(1, 20);
        this.damage = 2;
    }

    @Override
    protected void interactWithTarget(MapSimulation map, Position currentPosition, Position targetPosition, Entity entity) {
        Creature target = (Creature) entity;
        target.takeDamage(this.getDamage());

        if (target.getHealth() <= 0){
            this.heal(getDamage());
            map.remove(currentPosition);
            map.add(targetPosition, this);
        }
    }

    @Override
    protected Class<? extends Entity> getTargetType() {
        return Herbivore.class;
    }
}
