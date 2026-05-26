package org.dm;

public class SettingsSimulation {

    private final Input input;
    private int width;
    private int height;

    private int healthHerbivore;
    private int speedHerbivore;

    private int healthPredator;
    private int speedPredator;
    private int damage;

    private int countEntities;
    private int countCreatures;
    private int countPredators;
    private int countHerbivores;
    private int countRocks;
    private int countGrass;
    private int countTrees;
    private int nutritionalValue;

    public SettingsSimulation() {
        this.input = new Input();
        installSettings();
    }

    /*
    1. количество сущностей 50% от карты
    2. количество существ 50% от сущностей
    3. количество хищников ровно 50 % от существ (должно быть строго столько же сколько и хищников)
    4. количество травоядных ровно 50 % от существ (должно быть строго столько же сколько и травоядных)
    5. количество камней 10 % от сущностей
    6. количество травы 25 % от сущностей
    7. количество дереьвьев 10 % от сущностей
     */

    private void installSettings(){
        // размеры от 10 до 40
        width = input.inputValue("ширина доски");
        height = input.inputValue("высота доски");
        // здоровье от 10 до 100
        healthHerbivore = input.inputValue("здоровье травоядного");
        healthPredator = input.inputValue("здоровье хищника");
        // скорость от 1 до 4
        speedHerbivore = input.inputValue("скорость травоядного");
        speedPredator = input.inputValue("скрость хищника");
        // урон от 1 до 4
        damage = input.inputValue("атака хищника");

        nutritionalValue = input.inputValue("питательность травы");
        calculated();
    }

    private void calculated(){
        int totalCells = width * height;

        countEntities = totalCells * 50 / 100;
        countCreatures = countEntities * 50 / 100;

        countHerbivores = countCreatures / 2;
        countPredators = countHerbivores;

        countCreatures = countHerbivores + countPredators;

        countRocks = countEntities * 10 / 100;
        countTrees = countEntities * 10 / 100;

        countGrass = countEntities - (countCreatures + countRocks + countTrees);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getHealthHerbivore() {
        return healthHerbivore;
    }

    public int getSpeedHerbivore() {
        return speedHerbivore;
    }

    public int getHealthPredator() {
        return healthPredator;
    }

    public int getSpeedPredator() {
        return speedPredator;
    }

    public int getDamage() {
        return damage;
    }

    public int getCountEntities() {
        return countEntities;
    }

    public int getCountCreatures() {
        return countCreatures;
    }

    public int getCountPredators() {
        return countPredators;
    }

    public int getCountHerbivores() {
        return countHerbivores;
    }

    public int getCountRocks() {
        return countRocks;
    }

    public int getCountGrass() {
        return countGrass;
    }

    public int getCountTrees() {
        return countTrees;
    }

    public int getNutritionalValue() {
        return nutritionalValue;
    }

    public static void main(String[] args) {
        SettingsSimulation setings = new SettingsSimulation();
        setings.installSettings();
    }
}

