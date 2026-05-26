package org.dm.actions.init_actions;

import org.dm.SettingsSimulation;
import org.dm.entities.Entity;
import org.dm.entities.static_object.Tree;

public class SpawnTree extends SpawnAction{
    public SpawnTree(SettingsSimulation settings) {
        super(settings, settings.getCountTrees());
    }

    @Override
    protected Entity spawn() {
        return new Tree();
    }
}
