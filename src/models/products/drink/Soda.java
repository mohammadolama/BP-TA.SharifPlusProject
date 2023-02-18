package models.products.drink;

import models.ingredient.Ingredients;
import models.products.drink.ColdDrink;

public class Soda extends ColdDrink {
    public Soda(String name) {
        super(name);
        this.ingredients.put(Ingredients.SODA , 3);
    }
}
