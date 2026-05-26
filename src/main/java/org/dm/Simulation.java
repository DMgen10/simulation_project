package org.dm;

import org.dm.actions.Action;
import org.dm.actions.ActionReproduction;
import org.dm.actions.HungryAction;
import org.dm.actions.MoveAction;
import org.dm.actions.init_actions.*;
import org.dm.entities.Entity;
import org.dm.entities.creatures.Herbivore;
import org.dm.entities.creatures.Predator;
import org.dm.entities.static_object.Grass;

import java.util.ArrayList;
import java.util.List;

public class Simulation {

    private final SettingsSimulation settings = new SettingsSimulation();
    private final MapSimulation map = new MapSimulation(settings.getWidth(), settings.getHeight());
    private final Render render = new Render(map);
    private int count = 0; // тестово

    private final List<Action> initActions = new ArrayList<>();
    private final List<Action> turnActions = new ArrayList<>();

    public void nextTurn(){
        for (Action action: turnActions){
            action.execute(map);
            System.out.println("Ход: " + count);
            showCountCreatures(map);
            showHPCreatures(map);
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
            nextTurn();
            if (isOver(map)){
//                showVictory(map);
                break;
            }
        }
    }

//    private void showVictory(MapSimulation map){
//        int countHerbivores = 0;
//        int countPredators = 0;
//
//        for (Entity entity: map.getMap().values()){
//            if (entity instanceof Herbivore){
//                countHerbivores++;
//            }
//            if (entity instanceof Predator){
//                countPredators++;
//            }
//        }
//        if (countHerbivores == 0){
//            System.out.println("Выжил вид хищников");
//        } else if (countPredators == 0){
//            System.out.println("Выжил вид травоядных");
//        }
//    }

    private boolean isOver(MapSimulation map){
        int countHerbivores = 0;
        int countPredators = 0;


        for (Entity entity: map.getMap().values()){
            if (entity instanceof Herbivore){
                countHerbivores++;
            }
            if (entity instanceof Predator){
                countPredators++;
            }
        }

        if (countHerbivores <= 0 || countPredators >= 0){
            if (countHerbivores == 0){
                System.out.println("Выжил вид хищников");
                return true;
            } else if (countPredators == 0){
                System.out.println("Выжил вид травоядных");
            }
            return true;
        }
        return false;
    }

    private void prepareAction(){
        initActions.add(new SpawnPredator(settings));
        initActions.add(new SpawnHerbivore(settings));
        initActions.add(new SpawnGrass(settings));
        initActions.add(new SpawnRock(settings));
        initActions.add(new SpawnTree(settings));
        turnActions.add(new MoveAction(map));
        turnActions.add(new GrassLoopAction(settings));
        turnActions.add(new HungryAction(map));
        turnActions.add(new ActionReproduction(map, settings));
    }

    public void showHPCreatures(MapSimulation map){
        int countHPHerb = 0;
        int countHPPred = 0;
        for (Entity count: map.getMap().values()){
            if (count instanceof Herbivore){
                countHPHerb += ((Herbivore) count).getHealth();
            }

            if (count instanceof Predator){
                countHPPred += ((Predator) count).getHealth();
            }
        }
        System.out.println("Суммарное здоровье травоядных: " + countHPHerb);
        System.out.println("Суммарное здоровье хищников: " + countHPPred);
    }

    private void showCountCreatures(MapSimulation map){
        int countPredators = 0;
        int countHerbivores = 0;
        int countGrass = 0;

        for (Entity count: map.getMap().values()){
            if (count instanceof Herbivore){
                countHerbivores++;
            }
            if (count instanceof Predator){
                countPredators++;
            }
            if (count instanceof Grass){
                countGrass++;
            }
        }

        System.out.println("Хищников: " + countPredators);
        System.out.println("Травоядных: " + countHerbivores);
        System.out.println("Травы: " + countGrass);
    }
    
    public static void main(String[] args) {
        Simulation simulation = new Simulation();
        simulation.startSimulation();
    }
}
