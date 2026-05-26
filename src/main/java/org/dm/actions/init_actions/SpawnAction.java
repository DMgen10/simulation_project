package org.dm.actions.init_actions;

import org.dm.MapSimulation;
import org.dm.Position;
import org.dm.SettingsSimulation;
import org.dm.actions.Action;
import org.dm.entities.Entity;

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

        // логика заполнения/расстановки
        //1. случайная позиция, если незанято - ставим сколько нужно
    }

    protected abstract Entity spawn();

}
