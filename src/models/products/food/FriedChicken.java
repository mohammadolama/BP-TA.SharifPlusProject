package models.products.food;

import models.ingredient.Ingredients;
import models.products.food.FastFood;

public class FriedChicken extends FastFood {
    public FriedChicken(String name) {
        super(name);
        this.ingredients.put(Ingredients.CHICKEN, 3);
        this.ingredients.put(Ingredients.POTATO, 2);
    }
}
