package org.dm.simulation.actions.init_actions;

import org.dm.simulation.SettingsSimulation;
import org.dm.simulation.entities.Entity;
import org.dm.simulation.entities.creatures.Predator;

public class SpawnPredator extends SpawnAction{

    public SpawnPredator(SettingsSimulation settings) {
        super(settings, settings.getCountPredators());
    }

    @Override
    protected Entity spawn() {
        return new Predator(settings.getSpeedPredator(), settings.getHealthPredator(), settings.getDamage());
    }
}
