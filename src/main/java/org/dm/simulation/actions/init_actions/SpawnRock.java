package org.dm.simulation.actions.init_actions;

import org.dm.simulation.SettingsSimulation;
import org.dm.simulation.entities.Entity;
import org.dm.simulation.entities.static_object.Rock;

public class SpawnRock extends SpawnAction{

    public SpawnRock(SettingsSimulation settings) {
        super(settings, settings.getCountRocks());
    }

    @Override
    protected Entity spawn() {
        return new Rock();
    }
}
