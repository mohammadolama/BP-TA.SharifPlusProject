package models;

import java.util.ArrayList;
import java.util.List;

public class User {

    private final String username;
    private final String password;
    private final UserType userType;
    private List<Order> orders;

    public User(String username, String password, UserType userType) {
        this.username = username;
        this.password = password;
        this.userType = userType;
        this.orders = new ArrayList<>();
    }

    public String getUsername() {
        return username;
    }


    public String getPassword() {
        return password;
    }

    public UserType getUserType() {
        return userType;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void addOrder(Order newOrder) {
        this.orders.add(newOrder);
    }

    public void removeOrder(Order order){
        this.orders.remove(order);
    }

    @Override
    public String toString() {
        return username;
    }
}
