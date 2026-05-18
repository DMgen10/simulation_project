package org.dm.path_finder;

import org.dm.MapSimulation;
import org.dm.Position;
import org.dm.entities.Entity;

import java.util.List;

public interface PathFinder {

    List<Position> find(MapSimulation map, Position start, Class<? extends Entity> targetType);
}
