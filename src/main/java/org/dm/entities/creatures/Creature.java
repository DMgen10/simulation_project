package org.dm.entities.creatures;

import org.dm.MapSimulation;
import org.dm.Position;
import org.dm.entities.Entity;

public class Creature extends Entity {

    private int speed;
    private int health;
    private Random random = new Random();
    private PathFinder pathFinder = new BFS();

    public Creature(int speed, int health) {
        this.speed = speed;
        this.health = health;
    }

    public void takeDamage(int damage){
        this.health = Math.max(0, this.health - damage);
    }

    public void heal(int value){
        this.health += value;
    }

    // в процессе реализации
    public void makeMove(MapSimulation map, Position currentPosition){
        map.remove(currentPosition);
        map.add(currentPosition, map.getEntity(currentPosition));
    }


}
