package org.dm.simulation.actions.init_actions;

import org.dm.simulation.model.MapSimulation;
import org.dm.simulation.model.Position;
import org.dm.simulation.config.SettingsSimulation;
import org.dm.simulation.actions.Action;
import org.dm.simulation.entities.Entity;

public abstract class SpawnAction implements Action {
    protected final SettingsSimulation settings;
    private final int countSpawn;

    public SpawnAction(SettingsSimulation settings, int count) {
        this.settings = settings;
        this.countSpawn = count;
    }

    @Override
    public void execute(MapSimulation map) {
        int spawned = 0;

        while (spawned < countSpawn){
            Position position = map.getRandomPosition();
            if (!map.isBusy(position)){
                map.add(position, spawn());
                spawned++;
            }
        }
    }
    protected abstract Entity spawn();
}
