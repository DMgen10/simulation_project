package org.dm.simulation.actions.init_actions;

import org.dm.simulation.config.SettingsSimulation;
import org.dm.simulation.entities.Entity;
import org.dm.simulation.entities.static_object.Tree;

public class SpawnTree extends SpawnAction{
    public SpawnTree(SettingsSimulation settings) {

        super(settings, settings.getCountTrees());
    }

    @Override
    protected Entity spawn() {
        return new Tree();
    }
}
