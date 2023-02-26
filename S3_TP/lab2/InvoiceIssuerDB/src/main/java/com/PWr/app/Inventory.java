package com.PWr.app;

import java.util.Vector;





// GRASP - Cohesion:
// Klasa Inventory odpowiada WYŁĄCZNIE za przechowywanie i przetwarzanie danych o towarze
final class Inventory {
    // GRASP - Creator:
    // Klasa Inventory bezpośrednio używa Item, więc ta klasa tworzy instancję klasy Item
    private Vector <Item> inventory;
    private int stock_items;

    Inventory () {
        this.inventory = new Vector <Item> (0);

        this.inventory.add(new Item("Bike", 5, 10000.00));
        this.inventory.add(new Item("Fork", 5, 1200.00));
        this.inventory.add(new Item("Dumper", 5, 1000.00));
        this.inventory.add(new Item("Wheel", 10, 500.00));
        this.inventory.add(new Item("Tyre", 10, 80.00));
        this.inventory.add(new Item("Breaks", 10, 600.00));
        this.inventory.add(new Item("Rotor", 10, 75.00));
        this.inventory.add(new Item("Handlebar", 5, 150.00));
        this.inventory.add(new Item("Grips", 5, 25.00));
        this.inventory.add(new Item("Dropper", 5, 180.00));
        this.inventory.add(new Item("Saddle", 5, 40.00));
        this.inventory.add(new Item("Helmet", 5, 750.00));
        this.inventory.add(new Item("Jersey", 5, 60.00));
        this.inventory.add(new Item("Pants", 5, 100.00));
        this.inventory.add(new Item("Shoes", 5, 120.00));
        this.inventory.add(new Item("Multitool", 5, 130.00));
        this.inventory.add(new Item("Tube", 10, 10.00));

        this.stock_items = this.inventory.size();
    }

    public void display () {
        System.out.printf("%-12s %-12s %7s %n", "Item", "Quantity", "Price");
        System.out.println("---------------------------------");
        for (int i = 0; i < this.stock_items; i++) {
            Item item = this.inventory.get(i);
            System.out.printf("%-15s %2d  %13.2f %n", item.getName(), item.getQuantity(), item.getPrice());
        }
        System.out.println("\n");
    }

    public Item getItem (final String name) {
        for (int i = 0; i < this.stock_items; i++) {
            Item item = this.inventory.get(i);
            if (name.equals(item.getName())) {
                return item;
            }
        }

        return null;
    }

    public void sellItem (final String name, final int quantity) {
        Item item = this.getItem(name);
        item.setQuantity(item.getQuantity() - quantity);
    }

    public void returnItem (final String name, final int quantitiy) {
        Item item = this.getItem(name);
        item.setQuantity(item.getQuantity() + quantitiy);
    }
}
