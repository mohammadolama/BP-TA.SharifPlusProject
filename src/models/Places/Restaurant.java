package models.Places;

import Managers.ProductManager;
import Managers.StoreManager;
import models.Order;
import models.products.Product;

import java.util.ArrayList;
import java.util.List;

public class Restaurant extends Store {

    List<Product> appetizers;
    List<Product> fastFood;
    List<Product> foodsInRestaurant;

    public Restaurant(StoreManager storeManager) {
        this.itemsInThisPlace = new ArrayList<>();
        this.appetizers = ProductManager.getInstance().getProduct("FrenchFries", "Salad");
        this.fastFood = ProductManager.getInstance().getProduct("Burger", "Pizza", "FriedChicken", "Steak");

        this.foodsInRestaurant = new ArrayList<>();
        this.foodsInRestaurant.addAll(this.appetizers);
        this.foodsInRestaurant.addAll(this.fastFood);
        this.storeManager = storeManager;
    }

    @Override
    public String getMenu() {
        StringBuilder stringBuilder = new StringBuilder("Appetizers: \n");
        for (Product app : appetizers) {
            stringBuilder.append("\t ")
                    .append(app.getName())
                    .append("  ")
                    .append(app.getRecipe())
                    .append(" ")
                    .append(ProductManager.getInstance().isAvailable(app)? "" : "Not Available")
                    .append("\n");
        }
        stringBuilder.append("FastFoods: \n");
        for (Product fast : fastFood) {
            stringBuilder.append("\t ")
                    .append(fast.getName())
                    .append("  ")
                    .append(fast.getRecipe())
                    .append(" ")
                    .append(ProductManager.getInstance().isAvailable(fast)? "" : "Not Available")
                    .append("\n");
        }
        return stringBuilder.toString();
    }

    @Override
    public String processOrder(String order) {
        return process(order, foodsInRestaurant);
    }

    @Override
    public void addOrder(String order) {
        String[] s = order.split(" ");
        Order order1 = new Order();
    }
}
