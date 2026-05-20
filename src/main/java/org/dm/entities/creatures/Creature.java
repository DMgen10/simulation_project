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

        Class<?extends Entity> targetClass = getTargetType();
        List<Position> path = pathFinder.find(map, currentPosition, targetClass);

        if (path.isEmpty()){
            makeRandomMove(map, currentPosition);
            return;
        }

        int targetIndex = Math.min(path.size() - 1, getSpeed() - 1);
        Position newPosition = path.get(targetIndex);

        if (targetIndex == path.size() - 1){
            Entity targetEntity = map.getEntity(newPosition);

            if (targetEntity != null){
                interactWithTarget(map, currentPosition, newPosition, targetEntity);
                return;
            }
        }

        map.remove(currentPosition);
        map.add(currentPosition, map.getEntity(currentPosition));
    }

    protected abstract void interactWithTarget(MapSimulation map, Position currentPosition, Position targetPosition, Entity entity);
    
    private void makeRandomMove(MapSimulation map,Position currentPosition){
        int[][] directions = {{0,1},{0,-1},{1,0},{-1,0}};

        List<Position> validMoves = new ArrayList<>();

        for (int[] direction: directions){
            int x = currentPosition.x() + direction[0];
            int y = currentPosition.y() + direction[1];

}
