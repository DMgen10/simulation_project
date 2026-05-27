package org.dm.simulation.path_finder;

import org.dm.simulation.model.MapSimulation;
import org.dm.simulation.model.Position;
import org.dm.simulation.entities.Entity;
import java.util.List;

public interface PathFinder {

    List<Position> find(MapSimulation map, Position start, Class<? extends Entity> targetType);
}
