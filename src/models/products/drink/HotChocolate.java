package models.products.drink;

import models.ingredient.Ingredients;

public class HotChocolate extends HotDrink {
    public HotChocolate(String name) {
        super(name);
        this.ingredients.put(Ingredients.CHOCOLATE, 2);
        this.ingredients.put(Ingredients.SUGAR, 1);

    }
}
