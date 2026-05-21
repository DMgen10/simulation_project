package org.dm;

import org.dm.actions.Action;
import org.dm.actions.MoveAction;
import org.dm.actions.init_actions.*;
import org.dm.entities.Entity;
import org.dm.entities.static_object.Grass;

import java.util.ArrayList;
import java.util.List;

public class Simulation {


    private MapSimulation map = new MapSimulation(10,10);
    private Render render = new Render(map);
    private int count = 0; // тестово

    private List<Action> initActions = new ArrayList<>();
    private List<Action> turnActions = new ArrayList<>();

    public void nextTurn(){
        for (Action action: turnActions){
            action.execute(map);
            System.out.println("Ход: " + count);
            map.showCountCreatures();
            render.rendering();
            count++;
            try {
                Thread.sleep(800);
            } catch (InterruptedException exception){
                Thread.currentThread().interrupt();
            }
        }
    }
    public void pauseSimulation(){}
    public void startSimulation(){
        prepareAction();
        for (Action action: initActions){
            action.execute(map);
        }

        while (true){
            grassGrowth(map);
            nextTurn();
        }
    }

    private void grassGrowth(MapSimulation map){
        int count = 0;
        for (Entity entity: map.getMap().values()){
            if (entity instanceof Grass){
                count++;
            }
        }
        if (count == 0){{
                Action spawnGrass = new SpawnGrass();
                spawnGrass.execute(map);
            }
        }
    }

    private void prepareAction(){
        initActions.add(new SpawnPredator());
        initActions.add(new SpawnHerbivore());
        initActions.add(new SpawnGrass());
        initActions.add(new SpawnRock());
        initActions.add(new SpawnTree());
        turnActions.add(new MoveAction(map));
    }

    public static void main(String[] args) {
        Simulation simulation = new Simulation();
        simulation.startSimulation();
    }
}
