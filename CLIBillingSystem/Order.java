package CLIBillingSystem;

import java.util.ArrayList;

class Order {
    ArrayList<Item> orderItems = new ArrayList<>();

    void addItemToOrder(Item item) {
        orderItems.add(item);
    }

    void generateBill() {
        double total = 0;
        System.out.println("Bill:");
        for (Item item : orderItems) {
            System.out.println(item.name + " - $" + item.price);
            total += item.price;
        }
        System.out.println("Total: $" + total);
    }
}
