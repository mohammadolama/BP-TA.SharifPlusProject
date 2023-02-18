package Managers;

import models.Order;
import models.Places.CoffeeShop;
import models.Places.Restaurant;
import models.Places.Store;
import models.Places.StoreType;
import models.User;
import models.products.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StoreManager {

    private static StoreManager storeManager;

    private final Restaurant restaurant;
    private final CoffeeShop coffeeShop;

    private Store currentLocation;

    private final List<Order> orders;

    private final LogicalAgent logicalAgent;



    public StoreManager(LogicalAgent logicalAgent) {
        orders = new ArrayList<>();
        this.restaurant = new Restaurant(this);
        this.coffeeShop = new CoffeeShop(this);
        this.logicalAgent = logicalAgent;
    }

    public static StoreManager getInstance(LogicalAgent logicalAgent) {
        if (storeManager == null) {
            storeManager = new StoreManager(logicalAgent);
        }
        return storeManager;
    }


    public void setLocation(StoreType type) {
        if (type == StoreType.RESTAURANT) {
            currentLocation = restaurant;
        } else if (type == StoreType.COFFEE_SHOP) {
            currentLocation = coffeeShop;
        } else {
            currentLocation = null;
        }
    }

    public String addOrder(String order) {
        String s = currentLocation.processOrder(order);
        if (!s.contains("correct")) {
            return s;
        } else {

            Order newOrder = new Order();
            newOrder.setProducts(processOrder(order));
            boolean available = ProductManager.getInstance().isAvailable(newOrder);
            if (!available) {
                return "We don't have enough ingredients to make your order ready :(";
            } else {
                User currentUser = logicalAgent.getCurrentUser();
                newOrder.setCustomer(currentUser);
                orders.add(newOrder);
                logicalAgent.saveOrderForUser(newOrder);
                ProductManager.getInstance().updateStorage(newOrder);
            }


            return "Order saved successfully.";

        }
    }

    private Map<Product, Integer> processOrder(String order) {
        String[] s = order.split(" ");
        Map<Product, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length; i += 2) {
            Product product = ProductManager.getInstance().getProduct(s[i]).get(0);
            map.put(product, Integer.parseInt(s[i + 1]));
        }
        return map;
    }


    public String getMenu() {
        return currentLocation.getMenu();
    }

    public String customerOrder(String s) {
        return currentLocation.processOrder(s);
    }

    public String getOrders() {
        StringBuilder sb = new StringBuilder();
        for (Order order : orders) {
            sb.append(order).append("\n");
        }
        return sb.toString();
    }

    public String cancelOrder(String s) {
        long id;
        try {
            id = Long.parseLong(s);
        } catch (Exception e) {
            return "ID is not valid.";
        }

        Order x = null;

        for (Order order : orders) {
            if (order.getId() == id) {
                x = order;
                break;
            }
        }
        if (x == null) {
            return "Order with id:" + id + " does not exists.";
        }
        orders.remove(x);
        x.getCustomer().removeOrder(x);
        return "Operation done successfully.";

    }
}
