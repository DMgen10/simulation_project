package org.dm.entities.creatures;

import org.dm.MapSimulation;
import org.dm.Position;
import org.dm.entities.Entity;

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
    protected void interactWithTarget(MapSimulation map, Position currentPosition, Position targetPosition, Entity targtEntity) {
        if (!(targtEntity instanceof Creature)){
            return;
        }

        Creature target = (Herbivore) targtEntity;
        target.takeDamage(this.getDamage());
        this.heal(getDamage());

        if (target.getHealth() <= 0){
            map.remove(targetPosition);
            System.out.printf("волк cъел травоядного: ");
        }
    }

    @Override
    protected Class<? extends Entity> getTargetType() {
        return Herbivore.class;
    }

    @Override
    public Creature createChild() {
        return new Predator(this.getSpeed(), 10);
    }
}
