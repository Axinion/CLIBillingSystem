package CLIBillingSystem;

import java.util.ArrayList;

class Inventory {
    ArrayList<Item> items = new ArrayList<>();

    void addItem(String name, double price) {
        items.add(new Item(name, price));
    }

    void displayItems() {
        System.out.println("Inventory:");
        for (int i = 0; i < items.size(); i++) {
            System.out.println((i + 1) + ". " + items.get(i).name + " - $" + items.get(i).price);
        }
    }
}
