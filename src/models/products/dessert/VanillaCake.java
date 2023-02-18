package models.products.dessert;

import models.ingredient.Ingredients;

public class VanillaCake extends Dessert{
    public VanillaCake(String name) {
        super(name);
        this.ingredients.put(Ingredients.VANILLA , 5);
        this.ingredients.put(Ingredients.EGG , 2);
        this.ingredients.put(Ingredients.FLOUR , 3);
    }
}
