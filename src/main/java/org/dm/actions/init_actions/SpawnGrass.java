package org.dm.actions.init_actions;

import org.dm.entities.Entity;
import org.dm.entities.static_object.Grass;

public class SpawnGrass extends SpawnAction {
    @Override
    protected Entity spawn() {
        return new Grass();
    }
}
