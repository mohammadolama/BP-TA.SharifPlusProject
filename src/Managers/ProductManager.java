package Managers;

import models.Order;
import models.Storage;
import models.ingredient.Ingredients;
import models.products.Product;
import models.products.appetizer.FrenchFries;
import models.products.appetizer.Salad;
import models.products.dessert.ChocolateCake;
import models.products.dessert.IceCream;
import models.products.dessert.VanillaCake;
import models.products.drink.Coffee;
import models.products.drink.HotChocolate;
import models.products.drink.Soda;
import models.products.drink.Tea;
import models.products.food.Burger;
import models.products.food.FriedChicken;
import models.products.food.Pizza;
import models.products.food.Steak;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ProductManager {


    private static ProductManager productManager;

    private final FrenchFries frenchFries;
    private final Salad salad;
    private final ChocolateCake chocolateCake;
    private final IceCream iceCream;
    private final VanillaCake vanillaCake;
    private final Coffee coffee;
    private final HotChocolate hotChocolate;
    private final Soda soda;
    private final Tea tea;
    private final Burger burger;
    private final FriedChicken friedChicken;
    private final Pizza pizza;
    private final Steak steak;

    private final List<Product> productList;
    private static HashMap<String, Product> productMap;

    private final Storage storage;


    private ProductManager() {
        this.frenchFries = new FrenchFries("FrenchFries");
        this.salad = new Salad("Salad");
        this.chocolateCake = new ChocolateCake("ChocolateCake");
        this.iceCream = new IceCream("IceCream");
        this.vanillaCake = new VanillaCake("VanillaCake");
        this.coffee = new Coffee("Coffee");
        this.hotChocolate = new HotChocolate("HotChocolate");
        this.soda = new Soda("Soda");
        this.tea = new Tea("Tea");
        this.burger = new Burger("Burger");
        this.friedChicken = new FriedChicken("FriedChicken");
        this.pizza = new Pizza("Pizza");
        this.steak = new Steak("Steak");

        productList = Arrays.asList(frenchFries, salad, chocolateCake, iceCream,
                vanillaCake, coffee, hotChocolate, soda, tea, burger, friedChicken,
                pizza, steak);

        productMap = new HashMap<>();
        for (Product product : productList) {
            productMap.put(product.getName(), product);
        }


        this.storage = new Storage();
    }


    public static ProductManager getInstance() {
        if (productManager == null) {
            productManager = new ProductManager();
        }
        return productManager;
    }


    public List<Product> getProduct(String... names) {
        List<Product> result = new ArrayList<>();
        for (String name : names) {
            result.add(productMap.get(name));
        }
        return result;
    }

    public String addAll(String x) {
        try {
            Integer.parseInt(x);
        } catch (Exception e) {
            return "Invalid Input Format.";
        }
        return storage.addAll(Integer.parseInt(x));
    }

    public String add(String x) {
        String[] s = x.split(" ");
        if (s.length % 2 == 1) {
            return "Invalid Input Format!";
        }
        try {
            for (int i = 1; i < s.length; i += 2) {
                Integer.parseInt(s[i]);
            }
        } catch (Exception e) {
            return "Invalid Order Format";
        }

        boolean allAvailable = true;
        for (int i = 0; i < s.length; i += 2) {
            boolean b = true;
            try {
                Ingredients.valueOf(s[i]);
                break;
            } catch (Exception e) {
                b = false;
            }
            allAvailable = allAvailable && b;
        }
        if (!allAvailable) {
            return "We dont have some of your items in storage!";
        }

        List<Ingredients> list = new ArrayList<>();
        List<Integer> count = new ArrayList<>();
        for (int i = 0; i < s.length; i++) {
            if (i % 2 == 0) {
                list.add(Ingredients.valueOf(s[i]));
            } else {
                count.add(Integer.parseInt(s[i]));
            }
        }
        return storage.updateIngredient(list, count);
    }

    public String status() {
        return storage.status();
    }

    public boolean isAvailable(Product product){
        return storage.haveEnoughIngredientsForProduct(product);
    }

    public boolean isAvailable(Order order){
        return storage.haveEnoughIngredientsForOrder(order);
    }

    public void updateStorage(Order newOrder) {
        storage.update(newOrder);
    }
}
