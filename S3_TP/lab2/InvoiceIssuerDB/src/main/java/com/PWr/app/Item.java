package com.PWr.app;





// GRASP - Cohesion:
// Klasa Item odpowiada WYŁĄCZNIE za przechowywanie elementów o strukturze (nazwa, liczba, cena)
final class Item {
    private String name;
    private int quantity;
    private double price;



    Item (final String name, final int quantity, final double price) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    public String getName () {
        return this.name;
    }

    public int getQuantity () {
        return this.quantity;
    }

    public double getPrice () {
        return this.price;
    }

    public void setQuantity (final int quantity) {
        this.quantity = quantity;
    }
}
