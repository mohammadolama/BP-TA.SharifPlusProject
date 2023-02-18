package models.products.food;

import models.ingredient.Ingredients;
import models.products.food.FastFood;

public class Pizza extends FastFood {


    public Pizza(String name) {
        super(name);
        this.ingredients.put(Ingredients.MEAT , 1);
        this.ingredients.put(Ingredients.VEGETABLE , 1);
        this.ingredients.put(Ingredients.BREAD , 1);

    }
}
