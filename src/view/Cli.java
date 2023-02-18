package view;

import Managers.LogicalAgent;
import models.Places.StoreType;
import models.UserType;

import java.util.Scanner;

public class Cli {

    private LogicalAgent logicalAgent;
    private final Scanner scanner;
    private UserType type;

    public Cli() {
        scanner = new Scanner(System.in);
    }

    public void start() {
        printWelcome();
    }

    private void printWelcome() {
        System.out.println("Welcome to our app. hope you enjoy :)");
        while (true) {
            if (type == null) {
                System.out.println("1.Login \t 2.Signup \t 3.Exit");
                String st = scanner.nextLine();
                if (st.equals("1")) {
                    spacer(2);
                    login();
                } else if (st.equals("2")) {
                    spacer(2);
                    signUp();
                } else if (st.equals("3")) {
                    System.exit(0);
                } else {
                    System.out.println("Invalid Input");
                }
            } else {
                loginMode(type);
            }
        }
    }

    private void loginMode(UserType type) {
        if (type == UserType.ADMIN) {
            adminLoginMode();
        } else if (type == UserType.CUSTOMER) {
            customerLoginMode();
        }
    }

    private void customerLoginMode() {
        System.out.println("Welcome. What do you want to do ?");
        while (true) {
            System.out.println("1. go to restaurant \t 2. go to coffee shop \t 3.see my orders history \t 4. sign out");
            String s = scanner.nextLine();
            if (s.equals("1")) {
                gotoStore(StoreType.RESTAURANT);
            } else if (s.equals("2")) {
                gotoStore(StoreType.COFFEE_SHOP);
            } else if (s.equals("3")) {
                System.out.println(logicalAgent.getOrdersOfUser());
                spacer(1);
            } else if (s.equals("4")) {
                type = null;
                break;
            } else {
                System.out.println("Invalid input");
            }
        }
    }

    private void gotoStore(StoreType storeType) {
        spacer(2);
        logicalAgent.gotoStore(storeType);
        System.out.println("Welcome. see our menu:");
        System.out.println(logicalAgent.getMenuOf());
        while (true) {
            System.out.println("What do you do? 1.See the menu again.\t 2.AddOrder \t3.Quit");
            String s = scanner.nextLine();
            if (s.equals("1")) {
                System.out.println(logicalAgent.getMenuOf());
            } else if (s.equals("2")) {
                customerOrder();
            } else if (s.equals("3")) {
                spacer(1);
                logicalAgent.gotoStore(null);
                break;
            } else {
                System.out.println("Invalid input");
            }
            spacer(1);
        }
    }

    private void customerOrder() {
        outer:
        while (true) {
            System.out.println("Enter name of products and count of each, seperated with an space");
            System.out.println("Example: Steak 1 Burger 2 Salad 5");
            String s = scanner.nextLine();
            while (true) {
                System.out.println("You ordered: " + s + ".Are you sure?\t 1.yes \t 2.retry \t 3.cancel");
                String s1 = scanner.nextLine();
                if (s1.equals("1")) {
                    String res = logicalAgent.customerOrder(s);
                    System.out.println(res);
                    spacer(1);
                    break outer;
                } else if (s1.equals("2")) {
                    spacer(1);
                    break;
                } else if (s1.equals("3")) {
                    spacer(1);
                    break outer;
                } else {
                    System.out.println("Invalid input");
                    spacer(1);
                }

            }
        }
    }


    private void adminLoginMode() {
        System.out.println("Welcome. What do you want to do ?");
        while (true) {
            System.out.println(" 1. See storage status \t 2. add x to all ingredients in store" +
                    " \t 3. increase value of some ingredients \t 4.Cancel an order. \t 5. sign out");
            String s = scanner.nextLine();
            if (s.equals("1")) {
                System.out.println(logicalAgent.storageStatus());
                spacer(1);
            } else if (s.equals("2")) {
                System.out.println("how mush do you want to add?");
                String s1 = scanner.nextLine();
                System.out.println(logicalAgent.storageAddAll(s1));
                spacer(1);
            } else if (s.equals("3")) {
                System.out.println("Enter name of ingredients and count of each, seperated with an space");
                System.out.println("Example: POTATO 7 SODA -3");
                String s1 = scanner.nextLine();
                System.out.println(logicalAgent.storageAddSome(s1));
                spacer(1);
            } else if (s.equals("4")) {
                cancelOrder();


            } else if (s.equals("5")) {
                type = null;
                break;
            } else {
                System.out.println("Invalid input");
            }
        }
    }

    private void cancelOrder() {
        String orders = logicalAgent.getOrders();
        if (orders.equals("")){
            System.out.println("There is no order to show.");
        }else {
            System.out.println(orders);
            while(true){
                System.out.println("enter id of an order to cancel it. enter 'back' to return.");
                String s = scanner.nextLine();
                if (s.equals("back")){
                    break;
                }else {
                    String res = logicalAgent.cancelOrder(s);
                    System.out.println(res);
                    spacer(1);
                    if (s.contains("successfully")){
                        break;
                    }
                }
            }
        }
        System.out.println();
    }

    private void spacer(int n) {
        String st = "";
        for (int i = 0; i < n; i++) {
            st = st.concat("\n");
        }
        System.out.println(st);
    }

    private void login() {
        outer1:
        while (true) {
            System.out.println("enter your username: ");
            String username = scanner.nextLine();
            System.out.println("enter your password: ");
            String password = scanner.nextLine();
            outer2:
            while (true) {
                System.out.println("Are you sure? 1.yes \t 2.cancel \t 3.retry");
                String s = scanner.nextLine();
                if (s.equals("1")) {
                    String res = logicalAgent.login(username, password);
                    System.out.println(res);
                    if (res.contains("successfully")) {
                        String[] s1 = res.split(" ");
                        type = UserType.valueOf(s1[s1.length - 1]);
                        break outer1;
                    } else {
                        while (true) {
                            System.out.println("1. retry \t 2. back");
                            String s1 = scanner.nextLine();
                            if (s1.equals("1")) {
                                break outer2;
                            } else if (s1.equals("2")) {
                                break outer1;
                            } else {
                                System.out.println("invalid");
                            }
                        }
                    }
                } else if (s.equals("2")) {
                    break outer1;
                } else if (s.equals("3")) {
                    break;
                }
            }
        }
    }

    private void back_retry() {
        while (true) {
            System.out.println("1. retry \t 2. back");
            String s1 = scanner.nextLine();
            if (s1.equals("2")) {
                break;
            }
        }
    }

    private void signUp() {
        outer1:
        while (true) {
            System.out.println("enter your username: ");
            String username = scanner.nextLine();
            System.out.println("enter your password: ");
            String password = scanner.nextLine();
            System.out.println("enter your type: ADMIN \t or \t CUSTOMER");
            String type = scanner.nextLine();
            outer2:
            while (true) {
                System.out.println("Are you sure? 1.yes \t 2.cancel \t 3.retry");
                String s = scanner.nextLine();
                if (s.equals("1")) {
                    String res = logicalAgent.signUp(username, password, type);
                    System.out.println(res);
                    if (res.contains("successfully")) {
                        break outer1;
                    } else {
                        while (true) {
                            System.out.println("1. retry \t 2. back");
                            String s1 = scanner.nextLine();
                            if (s1.equals("1")) {
                                break outer2;
                            } else if (s1.equals("2")) {
                                break outer1;
                            } else {
                                System.out.println("invalid");
                            }
                        }
                    }
                } else if (s.equals("2")) {
                    break outer1;
                } else if (s.equals("3")) {
                    break;
                }
            }
        }
    }

    public LogicalAgent getLogicalAgent() {
        return logicalAgent;
    }

    public void setLogicalAgent(LogicalAgent logicalAgent) {
        this.logicalAgent = logicalAgent;
    }
}
