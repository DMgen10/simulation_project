package org.dm.actions.init_actions;

import org.dm.entities.Entity;
import org.dm.entities.static_object.Rock;

public class SpawnRock extends SpawnAction{
    @Override
    protected Entity spawn() {
        return new Rock();
    }
}
