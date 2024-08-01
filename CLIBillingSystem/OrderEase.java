package CLIBillingSystem;

import java.util.HashMap;
import java.util.Scanner;

public class OrderEase {
    private static HashMap<String, User> users = new HashMap<>();
    private static Inventory inventory = new Inventory();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        seedData();

        while (true) {
            System.out.println("1. Login");
            System.out.println("2. Sign-Up");
            System.out.println("3. Exit");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    login(scanner);
                    break;
                case 2:
                    signUp(scanner);
                    break;
                case 3:
                    System.out.println("Exiting...");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private static void seedData() {
        users.put("admin", new User("admin", "admin", true));
        inventory.addItem("Burger", 5.99);
        inventory.addItem("Pizza", 8.99);
        inventory.addItem("Soda", 1.99);
    }

    private static void login(Scanner scanner) {
        System.out.print("Username: ");
        String username = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();

        if (users.containsKey(username) && users.get(username).password.equals(password)) {
            User user = users.get(username);
            if (user.isAdmin) {
                adminMenu(scanner);
            } else {
                customerMenu(scanner);
            }
        } else {
            System.out.println("Invalid username or password.");
        }
    }

    private static void signUp(Scanner scanner) {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        if (!users.containsKey(username)) {
            users.put(username, new User(username, password, false));
            System.out.println("Sign-Up successful. You can now log in.");
        } else {
            System.out.println("Username already exists. Try a different one.");
        }
    }

    private static void adminMenu(Scanner scanner) {
        while (true) {
            System.out.println("Admin Menu:");
            System.out.println("1. View Inventory");
            System.out.println("2. Add Item to Inventory");
            System.out.println("3. Logout");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    inventory.displayItems();
                    break;
                case 2:
                    System.out.print("Enter item name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter item price: ");
                    double price = scanner.nextDouble();
                    inventory.addItem(name, price);
                    System.out.println("Item added successfully.");
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private static void customerMenu(Scanner scanner) {
        Order order = new Order();

        while (true) {
            System.out.println("Customer Menu:");
            System.out.println("1. View Menu");
            System.out.println("2. Place Order");
            System.out.println("3. Generate Bill");
            System.out.println("4. Logout");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    inventory.displayItems();
                    break;
                case 2:
                    System.out.print("Enter item number: ");
                    int itemNumber = scanner.nextInt();
                    if (itemNumber > 0 && itemNumber <= inventory.items.size()) {
                        order.addItemToOrder(inventory.items.get(itemNumber - 1));
                        System.out.println("Item added to order.");
                    } else {
                        System.out.println("Invalid item number. Try again.");
                    }
                    break;
                case 3:
                    order.generateBill();
                    return;
                case 4:
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}
