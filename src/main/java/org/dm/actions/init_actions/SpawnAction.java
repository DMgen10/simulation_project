package org.dm.actions.init_actions;

import org.dm.MapSimulation;
import org.dm.Position;
import org.dm.actions.Action;
import org.dm.entities.Entity;

import java.util.Random;

public abstract class SpawnAction implements Action {

    private Random random = new Random();
    private int count = 5;

    @Override
    public void execute(MapSimulation map) {

        while (count > 0){
            int width = random.nextInt(0,map.getWidth());
            int height = random.nextInt(0,map.getHeight());
            Position position = new Position(width,height);
            if (!map.isBusy(position)){
                map.add(position, spawn());
                count--;
            }
        }

        // логика заполнения/расстановки
        //1. случайная позиция, если незанято - ставим сколько нужно
    }

    protected abstract Entity spawn();

}
