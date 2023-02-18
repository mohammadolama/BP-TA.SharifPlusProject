package models.products.drink;

import models.ingredient.Ingredients;

public class Coffee extends HotDrink {
    public Coffee(String name) {
        super(name);
        this.ingredients.put(Ingredients.COFFEE, 2);
        this.ingredients.put(Ingredients.SUGAR, 1);
    }
}
