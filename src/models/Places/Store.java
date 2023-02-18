package models.Places;

import Managers.StoreManager;
import models.products.Product;

import java.util.List;

public abstract class Store {

    List<List<Product>> itemsInThisPlace;
    StoreManager storeManager;


    public abstract String getMenu();

    public abstract String processOrder(String order);

    public abstract void addOrder(String order);

    protected String process(String order, List<Product> foodInCoffeeShop) {
        String[] s = order.split(" ");
        try {
            for (int i = 1; i < s.length; i += 2) {
                Integer.parseInt(s[i]);
            }
        } catch (Exception e) {
            return "Invalid Order Format";
        }
        boolean allAvailable = true;
        for (int i = 0; i < s.length; i += 2) {
            boolean b = false;
            for (Product product : foodInCoffeeShop) {
                if (product.getName().equalsIgnoreCase(s[i])) {
                    b = true;
                    break;
                }
            }
            allAvailable = allAvailable && b;
        }
        if (!allAvailable) {
            return "We dont serve some of your orders here";
        }
        return "Order Format is correct.";
    }
}
