package Managers;

import models.Order;
import models.User;
import models.UserType;

import java.util.ArrayList;
import java.util.List;

public class UserManager {

    private List<User> users;
    private static UserManager userManager;

    private User currentUser;


    private UserManager() {
        this.users = new ArrayList<>();
        initialize();
    }

    private void initialize() {
        User customer = new User("c", "c", UserType.CUSTOMER);
        User admin = new User("a", "a", UserType.ADMIN);
        this.users.add(customer);
        this.users.add(admin);

    }

    private boolean doesUsernameExists(String username) {
        for (User user : this.users) {
            if (user.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

    private boolean doesPasswordMatch(String username, String password) {
        for (User user : this.users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }


    private User find(String username){
        for (User user : this.users) {
            if (user.getUsername().equals(username)){
                return user;
            }
        }
        return null;
    }

    public String signUp(String username, String password, String userType) {

        try{
            UserType.valueOf(userType.toUpperCase());
        }catch (Exception e){
            return "userType is invalid";
        }
        if (doesUsernameExists(username)) {
            return "Username already exists";
        } else {
            User user = new User(username, password, UserType.valueOf(userType));
            this.users.add(user);
            return "User saved successfully.";
        }
    }

    public String login(String username, String password) {
        if (!doesUsernameExists(username)) {
            return String.format("User with username \"%s\" does not exist", username);
        }else if (!doesPasswordMatch(username , password)){
            return "Password does not match.";
        }else {
            this.currentUser = find(username);
            return "Logged in successfully as " + currentUser.getUserType();
        }
    }

    public static UserManager getInstance() {
        if (userManager == null) {
            userManager = new UserManager();
        }
        return userManager;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public void saveOrderForUser(Order newOrder) {
        currentUser.addOrder(newOrder);
    }

    public String getOrdersOfUser(){
        return currentUser.getOrders().toString();
    }
}
