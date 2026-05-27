package org.dm.simulation.actions.init_actions;

import org.dm.simulation.config.SettingsSimulation;
import org.dm.simulation.entities.Entity;
import org.dm.simulation.entities.creatures.Herbivore;

public class SpawnHerbivore extends SpawnAction{

    public SpawnHerbivore(SettingsSimulation settings) {
        super(settings, settings.getCountHerbivores());
    }

    @Override
    protected Entity spawn() {
        return new Herbivore(settings.getSpeedHerbivore(), settings.getHealthHerbivore());
    }
}
