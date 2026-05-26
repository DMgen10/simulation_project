package org.dm.actions.init_actions;

import org.dm.MapSimulation;
import org.dm.SettingsSimulation;
import org.dm.entities.Entity;
import org.dm.entities.static_object.Grass;

public class GrassLoopAction extends SpawnAction {


    public GrassLoopAction(SettingsSimulation settings) {
        super(settings ,settings.getCountGrass());
    }

    @Override
    public void execute(MapSimulation map) {
            int count = settings.getCountGrass();
            for (Entity entity: map.getMap().values()){
                if (entity instanceof Grass){
                    count++;
                }
            }
            if (count == 0){{
                spawn();
            }
            }
    }

    @Override
    protected Entity spawn() {
        return new Grass(settings.getNutritionalValue());

    }
}
