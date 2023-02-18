package models.products.appetizer;

import models.ingredient.Ingredients;

public class Salad extends Appetizer {
    public Salad(String name) {
        super(name);
        this.ingredients.put(Ingredients.VEGETABLE , 3);
        this.ingredients.put(Ingredients.SALT, 1);
        this.ingredients.put(Ingredients.TOMATO, 1);
        this.ingredients.put(Ingredients.CUCUMBER, 2);

    }
}
