package models.products.appetizer;

import models.ingredient.Ingredients;
import models.products.appetizer.Appetizer;

public class FrenchFries extends Appetizer {
    public FrenchFries(String name) {
        super(name);
        this.ingredients.put(Ingredients.POTATO , 2);
    }
}
