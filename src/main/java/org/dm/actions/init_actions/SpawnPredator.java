package org.dm.actions.init_actions;

import org.dm.entities.Entity;
import org.dm.entities.creatures.Predator;

public class SpawnPredator extends SpawnAction{
    @Override
    protected Entity spawn() {
        return new Predator(1, 20);
    }
}
