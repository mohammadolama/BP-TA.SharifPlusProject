package Managers;

import models.Order;
import models.Places.StoreType;
import models.User;
import view.Cli;

public class LogicalAgent {

    private final UserManager userManager;
    private final ProductManager productManager;
    private final StoreManager storeManager;
    private Cli cli;

    public LogicalAgent() {
        userManager = UserManager.getInstance();
        productManager = ProductManager.getInstance();
        storeManager = StoreManager.getInstance(this);
    }

    public Cli getCli() {
        return cli;
    }

    public void setCli(Cli cli) {
        this.cli = cli;
    }

    public String login(String username, String password) {
        return userManager.login(username, password);
    }

    public String signUp(String username, String password, String type) {
        return userManager.signUp(username, password, type);
    }

    public void gotoStore(StoreType type) {
        storeManager.setLocation(type);
    }


    public String getMenuOf() {
        return storeManager.getMenu();
    }

    public String customerOrder(String s) {
        return storeManager.addOrder(s);
    }

    public String storageStatus() {
        return productManager.status();
    }

    public String storageAddAll(String x) {
        return productManager.addAll(x);
    }

    public String storageAddSome(String x) {
        return productManager.add(x);
    }

    User getCurrentUser() {
        return userManager.getCurrentUser();
    }

    public void saveOrderForUser(Order newOrder) {
        userManager.saveOrderForUser(newOrder);
    }

    public String getOrdersOfUser() {
        return userManager.getOrdersOfUser();
    }

    public String getOrders() {
        return storeManager.getOrders();
    }

    public String cancelOrder(String s) {
        return storeManager.cancelOrder(s);
    }
}
