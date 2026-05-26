package org.dm.actions.init_actions;

import org.dm.SettingsSimulation;
import org.dm.entities.Entity;
import org.dm.entities.creatures.Herbivore;

public class SpawnHerbivore extends SpawnAction{

    public SpawnHerbivore(SettingsSimulation settings) {
        super(settings, settings.getCountHerbivores());
    }

    @Override
    protected Entity spawn() {
        return new Herbivore(settings.getSpeedHerbivore(), settings.getHealthHerbivore());
    }
}
