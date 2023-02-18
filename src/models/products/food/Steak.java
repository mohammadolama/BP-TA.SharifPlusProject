package models.products.food;

import models.ingredient.Ingredients;
import models.products.food.FastFood;

public class Steak extends FastFood {

    public Steak(String name) {
        super(name);
        this.ingredients.put(Ingredients.MEAT, 3);
    }
}
