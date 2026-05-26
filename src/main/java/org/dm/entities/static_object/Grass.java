package org.dm.entities.static_object;

public class Grass extends Inanimate {

    private int nutritionValue;

    public Grass(int nutritionValue) {
        this.nutritionValue = nutritionValue;
    }

    public int getNutritionValue() {
        return nutritionValue;
    }
}
