package org.dm.simulation;

import org.dm.simulation.actions.Action;
import org.dm.simulation.actions.ActionReproduction;
import org.dm.simulation.actions.HungryAction;
import org.dm.simulation.actions.MoveAction;
import org.dm.simulation.actions.init_actions.*;
import org.dm.simulation.entities.Entity;
import org.dm.simulation.entities.creatures.Herbivore;
import org.dm.simulation.entities.creatures.Predator;
import org.dm.simulation.entities.static_object.Grass;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Simulation {

    private final SettingsSimulation settings = new SettingsSimulation();
    private final MapSimulation map = new MapSimulation(settings.getWidth(), settings.getHeight());
    private final Render render = new Render(map);
    private int count = 0;
    private final Scanner scanner = new Scanner(System.in);
    private volatile boolean isPaused = false;
    private volatile boolean isRunning = true;

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
    private void pauseSimulation(){
        this.isPaused =true;
        System.out.println("Симуляция поставлена на паузу");
    }

    private void resumeSimulation(){
        this.isPaused = false;
        System.out.println("Симуляция продолжается");
    }

    private void listenToUserInput(){
        while (isRunning){
            if (scanner.hasNextLine()){
                String input = scanner.nextLine().trim().toLowerCase();

                if (input.equals("p") && !isPaused){
                    pauseSimulation();
                }

                if (input.equals("s") && isPaused){
                    resumeSimulation();
                }
            }
        }
    }

    public void startSimulation() {
        prepareAction();
        for (Action action : initActions) {
            action.execute(map);
        }

        Thread inputThread = new Thread(this::listenToUserInput);
        inputThread.setDaemon(true);
        inputThread.start();

        while (isRunning) {
            if (isOver(map)) {
                isRunning = false;
                break;
            }
            if (!isPaused) {
                nextTurn();
            } else {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException exception) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

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
                return true;
            }
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
   }
