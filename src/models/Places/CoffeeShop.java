package models.Places;

import Managers.ProductManager;
import Managers.StoreManager;
import models.products.Product;

import java.util.ArrayList;
import java.util.List;

public class CoffeeShop extends Store {

    private final List<Product> drinks;
    private final List<Product> desserts;
    private final List<Product> foodInCoffeeShop;

    public CoffeeShop(StoreManager storeManager) {
        this.itemsInThisPlace = new ArrayList<>();

        drinks = ProductManager.getInstance()
                .getProduct("Tea", "Soda", "Coffee", "HotChocolate");

        desserts = ProductManager.getInstance()
                .getProduct("ChocolateCake", "IceCream", "VanillaCake");


        foodInCoffeeShop = new ArrayList<>();
        foodInCoffeeShop.addAll(drinks);
        foodInCoffeeShop.addAll(desserts);


        this.storeManager = storeManager;
    }

    @Override
    public String getMenu() {
        StringBuilder stringBuilder = new StringBuilder("Drinks: \n");
        for (Product drink : drinks) {
            stringBuilder.append("\t ")
                    .append(drink.getName())
                    .append("  ")
                    .append(drink.getRecipe())
                    .append(" ")
                    .append(ProductManager.getInstance().isAvailable(drink)? "" : "Not Available")
                    .append("\n");
        }
        stringBuilder.append("Desserts: \n");
        for (Product dessert : desserts) {
            stringBuilder.append("\t ")
                    .append(dessert.getName())
                    .append("  ")
                    .append(dessert.getRecipe())
                    .append(" ")
                    .append(ProductManager.getInstance().isAvailable(dessert)? "" : "Not Available")
                    .append("\n");
        }
        return stringBuilder.toString();

    }

    @Override
    public String processOrder(String order) {
        return process(order, foodInCoffeeShop);
    }



    @Override
    public void addOrder(String order) {

    }
}
