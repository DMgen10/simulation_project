package org.dm.simulation.actions.init_actions;

import org.dm.simulation.SettingsSimulation;
import org.dm.simulation.entities.Entity;
import org.dm.simulation.entities.static_object.Grass;

public class SpawnGrass extends SpawnAction {

    public SpawnGrass(SettingsSimulation settings) {
        super(settings, settings.getCountGrass());
    }

    @Override
    protected Entity spawn() {
        return new Grass(settings.getNutritionalValue());
    }
}
