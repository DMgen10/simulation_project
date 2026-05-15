package org.dm.actions.init_actions;

import org.dm.entities.Entity;
import org.dm.entities.static_object.Tree;

public class SpawnTree extends SpawnAction{
    @Override
    protected Entity spawn() {
        return new Tree();
    }
}
