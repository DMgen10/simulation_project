    package org.dm.path_finder;

    import org.dm.MapSimulation;
    import org.dm.Position;
    import org.dm.entities.Entity;

    import java.util.*;

    public class BFS implements PathFinder{
        @Override
        public List<Position> find(MapSimulation map, Position start, Class<? extends Entity> targetType) {

            Queue<Position> queue = new LinkedList<>();
            Set<Position> visited = new HashSet<>();
            Map<Position,Position> parentmap = new HashMap<>();

            queue.add(start);
            visited.add(start);

            while (!queue.isEmpty()){
                Position current = queue.poll();
                Entity entity =  map.getEntity(current);

                if (targetType.isInstance(entity) && !current.equals(start)){
                    return buildPath(parentmap, current);
                }

                for (Position neighbor:getValidNeighbors(map, current, targetType)){
                    if (!visited.contains(neighbor)){
                        visited.add(neighbor);
                        parentmap.put(neighbor, current);
                        queue.add(neighbor);
                    }
                }

            }
            return Collections.emptyList();
        }

        private List<Position> buildPath(Map<Position, Position> parentmap, Position target) {
            List<Position> path = new ArrayList<>();
            Position current = target;

            while (parentmap.containsKey(current)){
                path.add(current);
                current = parentmap.get(current);
            }
            Collections.reverse(path);
            return path;
        }

        private List<Position> getValidNeighbors(MapSimulation map, Position current, Class<? extends Entity> targetType){
            List<Position> neighbors = new LinkedList<>();
            int[][] directions = {{1,0},{-1,0},{0,1},{0,-1}};

            for (int[] direction : directions){
                int x = current.x() + direction[0];
                int y = current.y() + direction[1];

                if (map.isAcceptableLimit(x, y)){
                    Position neighbor = new Position(x, y);
                    Entity entity = map.getEntity(neighbor);

                    if (entity == null || targetType.isInstance(entity)){
                        neighbors.add(neighbor);
                    }
                }
            }
            return neighbors;
        }
    }
