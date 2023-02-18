package models;

import models.ingredient.Ingredients;
import models.products.Product;

import java.util.*;

public class Storage {
    private HashMap<Ingredients, Integer> storageCount;


    public Storage() {
        storageCount = new HashMap<>();
        initialize();
    }

    private void initialize() {
        Random random = new Random();
        for (Ingredients value : Ingredients.values()) {
            storageCount.put(value, random.nextInt(50));
        }
    }


    public String status() {
        StringBuilder stringBuilder = new StringBuilder();

        for (Map.Entry<Ingredients, Integer> entry : storageCount.entrySet()) {
            stringBuilder
                    .append(entry.getKey())
                    .append("  :  ")
                    .append(entry.getValue())
                    .append("\n");
        }
        return stringBuilder.toString();
    }


    private int getMin() {
        return Collections.min(storageCount.values());
    }

    public String addAll(int x) {
        if (x < 0 && Math.abs(x) > getMin()) {
            return "Storage count can not be negative!";
        }
        storageCount.replaceAll((i, v) -> storageCount.get(i) + x);
        return "Operation done successfully!";
    }


    public String updateIngredient(List<Ingredients> list, List<Integer> count) {
        if (list.size() != count.size()) {
            return "Invalid Input Format!";
        }
        boolean b = true;
        for (int i = 0; i < list.size(); i++) {
            if (count.get(i) < 0 && storageCount.get(list.get(i)) < Math.abs(count.get(i))) {
                b = false;
                break;
            }
        }
        if (!b) {
            return "Storage count can not be negative!";
        }

        for (int i = 0; i < list.size(); i++) {
            storageCount.put(list.get(i), storageCount.get(list.get(i)) + count.get(i));

        }
        return "Operation done successfully!";
    }


    private HashMap<Ingredients, Integer> getTotalIngredients(Order order) {
        HashMap<Ingredients, Integer> total = new HashMap<>();
        for (Ingredients value : Ingredients.values()) {
            total.put(value, 0);
        }
        for (Map.Entry<Product, Integer> entry1 : order.getProducts().entrySet()) {
            for (Map.Entry<Ingredients, Integer> entry : entry1.getKey().getIngredients().entrySet()) {
                total.put(entry.getKey(), total.get(entry.getKey()) + entry1.getValue() * entry.getValue());
            }
        }
        return total;
    }

    public boolean haveEnoughIngredientsForOrder(Order order) {

        HashMap<Ingredients, Integer> total = getTotalIngredients(order);


        boolean b = true;
        for (Map.Entry<Ingredients, Integer> entry : total.entrySet()) {
            if (entry.getValue() != 0 && storageCount.get(entry.getKey()) < entry.getValue()) {
                b = false;
                break;
            }
        }
        return b;
    }

    public boolean haveEnoughIngredientsForProduct(Product product) {
        boolean b = true;
        for (Map.Entry<Ingredients, Integer> entry : product.getIngredients().entrySet()) {
            if (entry.getValue() != 0 && storageCount.get(entry.getKey()) < entry.getValue()) {
                b = false;
                break;
            }
        }
        return b;
    }

    public void update(Order newOrder) {
        HashMap<Ingredients, Integer> total = getTotalIngredients(newOrder);

        for (Map.Entry<Ingredients, Integer> entry : total.entrySet()) {
            if (entry.getValue() != 0)
                storageCount.put(entry.getKey(), storageCount.get(entry.getKey()) - entry.getValue());
        }
    }
}
