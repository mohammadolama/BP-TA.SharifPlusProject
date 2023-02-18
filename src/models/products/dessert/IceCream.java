package models.products.dessert;

import models.ingredient.Ingredients;

public class IceCream extends Dessert{
    public IceCream(String name) {
        super(name);
        this.ingredients.put(Ingredients.SUGAR, 2);
        this.ingredients.put(Ingredients.ICE_CREAM, 2);

    }
}
