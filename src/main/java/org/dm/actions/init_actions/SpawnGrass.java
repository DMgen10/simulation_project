package org.dm.actions.init_actions;

import org.dm.SettingsSimulation;
import org.dm.entities.Entity;
import org.dm.entities.static_object.Grass;

public class SpawnGrass extends SpawnAction {

    public SpawnGrass(SettingsSimulation settings) {
        super(settings, settings.getCountGrass());
    }

    @Override
    protected Entity spawn() {
        return new Grass(settings.getNutritionalValue());
    }
}
