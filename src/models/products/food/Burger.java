package models.products.food;

import models.ingredient.Ingredients;
import models.products.food.FastFood;

public class Burger extends FastFood {

    public Burger(String name) {
        super(name);
        this.ingredients.put(Ingredients.MEAT, 3);
        this.ingredients.put(Ingredients.BREAD, 1);
    }
}
