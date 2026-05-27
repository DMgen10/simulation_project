package org.dm.simulation.actions.init_actions;

import org.dm.simulation.MapSimulation;
import org.dm.simulation.Position;
import org.dm.simulation.SettingsSimulation;
import org.dm.simulation.entities.Entity;
import org.dm.simulation.entities.static_object.Grass;

public class GrassLoopAction extends SpawnAction {

    public GrassLoopAction(SettingsSimulation settings) {
        super(settings ,settings.getCountGrass());
    }

    @Override
    public void execute(MapSimulation map) {
            int count = 0;
            for (Entity entity: map.getMap().values()){
                if (entity instanceof Grass){
                    count++;
                }
            }
            if (count == 0){{
                for (int i = 0; i < settings.getCountGrass(); i++) {
                    Position position = map.getRandomPosition();
                    map.add(position,spawn());
                }
            }
            }
    }

    @Override
    protected Entity spawn() {
        return new Grass(settings.getNutritionalValue());
    }
}
