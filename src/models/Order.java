package models;

import models.products.Product;

import java.util.Map;

public class Order {

    private final long id;
    private User customer;
    private Map<Product , Integer> products;

    public Order() {
        this.id = System.nanoTime();
    }

    public long getId() {
        return id;
    }

    public User getCustomer() {
        return customer;
    }

    public void setCustomer(User customer) {
        this.customer = customer;
    }

    public Map<Product, Integer> getProducts() {
        return products;
    }

    public void setProducts(Map<Product, Integer> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "{" +
                "id: " + id +
                ", customer: " + customer +
                ", products: " + products.entrySet() +
                '}';
    }
}
