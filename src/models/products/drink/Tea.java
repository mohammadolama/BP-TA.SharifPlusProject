package models.products.drink;

import models.ingredient.Ingredients;

public class Tea extends HotDrink {
    public Tea(String name) {
        super(name);
        this.ingredients.put(Ingredients.TEA, 2);
        this.ingredients.put(Ingredients.SUGAR, 1);
    }
}
