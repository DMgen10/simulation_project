package org.dm.actions.init_actions;

import org.dm.entities.Entity;
import org.dm.entities.creatures.Herbivore;

public class SpawnHerbivore extends SpawnAction{
    @Override
    protected Entity spawn() {
        return new Herbivore();
    }
}
