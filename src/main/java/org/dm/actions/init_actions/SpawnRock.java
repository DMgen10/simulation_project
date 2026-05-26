package org.dm.actions.init_actions;

import org.dm.SettingsSimulation;
import org.dm.entities.Entity;
import org.dm.entities.static_object.Rock;

public class SpawnRock extends SpawnAction{

    public SpawnRock(SettingsSimulation settings) {
        super(settings, settings.getCountRocks());
    }

    @Override
    protected Entity spawn() {
        return new Rock();
    }
}
