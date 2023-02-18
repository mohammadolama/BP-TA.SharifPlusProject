package models.products;

import models.ingredient.Ingredients;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Product {
    protected String name;
    protected HashMap<Ingredients, Integer> ingredients;


    public Product(String name) {
        this.name = name;
        this.ingredients = new HashMap<>();
    }

    public HashMap<Ingredients, Integer> getIngredients() {
        return ingredients;
    }

    public String getRecipe() {
        StringBuilder stringBuilder = new StringBuilder("[");
        for (Map.Entry<Ingredients, Integer> entry : ingredients.entrySet()) {
            stringBuilder.append(entry.getKey()).append(" : ").append(entry.getValue()).append(", ");
        }
        stringBuilder.reverse();
        stringBuilder.delete(0, 2);
        stringBuilder.reverse();
        stringBuilder.append("]");
        return stringBuilder.toString();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setIngredients(HashMap<Ingredients, Integer> ingredients) {
        this.ingredients = ingredients;
    }

    @Override
    public String toString() {
        return name;
    }
}
