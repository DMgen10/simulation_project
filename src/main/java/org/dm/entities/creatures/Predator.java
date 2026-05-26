package org.dm.entities.creatures;

import org.dm.MapSimulation;
import org.dm.Position;
import org.dm.entities.Entity;

public class Predator extends Creature {

    private final int damage;

    public int getDamage() {
        return damage;
    }

    public Predator(int speed, int health, int damage) {
        super(speed, health);
        this.damage = damage;
    }

    @Override
    protected void interactWithTarget(MapSimulation map, Position currentPosition, Position targetPosition, Entity targtEntity) {
        if (!(targtEntity instanceof Creature)){
            return;
        }

        Creature target = (Herbivore) targtEntity;
        target.takeDamage(this.getDamage());
        this.heal(getDamage());

        if (target.getHealth() <= 0){
            map.remove(targetPosition);
        }
    }

    @Override
    protected Class<? extends Entity> getTargetType() {
        return Herbivore.class;
    }

    @Override
    public Creature createChild() {
        return new Predator(this.getSpeed(),getHealth(), getDamage());
    }
}
