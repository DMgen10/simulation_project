package org.dm;

public class SettingsSimulation {

    private Input input;
    private int width;
    private int height;

    private int healthHerbivore;
    private int speedHerbivore;

    private int healthPredator;
    private int speedPredator;
    private int damage;

    public SettingsSimulation() {
        this.input = new Input();
        installSettings();
    }

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
}
