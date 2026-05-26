package org.dm.actions.init_actions;

import org.dm.SettingsSimulation;
import org.dm.entities.Entity;
import org.dm.entities.creatures.Predator;

public class SpawnPredator extends SpawnAction{

    public SpawnPredator(SettingsSimulation settings) {
        super(settings, settings.getCountPredators());
    }

    @Override
    protected Entity spawn() {
        return new Predator(settings.getSpeedPredator(), settings.getHealthPredator(), settings.getDamage());
    }
}
