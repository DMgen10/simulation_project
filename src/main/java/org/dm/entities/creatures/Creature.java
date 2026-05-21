    package org.dm.entities.creatures;

    import org.dm.MapSimulation;
    import org.dm.Position;
    import org.dm.entities.Entity;
    import org.dm.path_finder.BFS;
    import org.dm.path_finder.PathFinder;

    import java.util.ArrayList;
    import java.util.List;
    import java.util.Random;

    public abstract class Creature extends Entity {

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

            if (path.size() - 1 <= getSpeed()){
                Position targetPosition = path.get(path.size() - 1);
                Entity targetEntity = map.getEntity(targetPosition);

                if (targetEntity != null){
                    interactWithTarget(map, currentPosition, targetPosition, targetEntity);
                    return;
                }
            }

    //        if (targetIndex == path.size() - 1){
    //            Entity targetEntity = map.getEntity(newPosition);
    //            if (targetEntity != null){
    //                interactWithTarget(map, currentPosition, newPosition, targetEntity);
    //                return;
    //            }
    //        }
            if (!map.isBusy(newPosition)){
                map.remove(currentPosition);
                map.add(newPosition, this);
            }

    //        map.remove(currentPosition);
    //        map.add(newPosition, this);
        }

        protected abstract void interactWithTarget(MapSimulation map, Position currentPosition, Position targetPosition, Entity entity);

        private void makeRandomMove(MapSimulation map,Position currentPosition){
            int[][] directions = {{0,1},{0,-1},{1,0},{-1,0}};

            List<Position> validMoves = new ArrayList<>();

            for (int[] direction: directions){
                int x = currentPosition.x() + direction[0];
                int y = currentPosition.y() + direction[1];

                if (map.isAcceptableLimit(x,y)){
                    Position position = new Position(x,y);
                    if (!map.isBusy(position)){
                        validMoves.add(position);
                    }
                }
            }
            if (!validMoves.isEmpty()){
                int randomIndex = random.nextInt(validMoves.size());
                Position newPosition = validMoves.get(randomIndex);

                map.remove(currentPosition);
                map.add(newPosition, this);
            }
        }

        protected abstract Class<? extends Entity> getTargetType();

        public int getSpeed() {
            return speed;
        }

        public int getHealth() {
            return health;
        }
    }
