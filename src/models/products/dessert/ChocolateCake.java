package models.products.dessert;

import models.ingredient.Ingredients;

public class ChocolateCake extends Dessert {
    public ChocolateCake(String name) {
        super(name);
        this.ingredients.put(Ingredients.FLOUR, 3);
        this.ingredients.put(Ingredients.EGG, 3);

    }
}
