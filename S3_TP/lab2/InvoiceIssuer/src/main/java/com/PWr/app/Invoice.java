package com.PWr.app;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.util.Vector;





// GRASP - Cohesion:
// Klasa Invoice odpowiada WYŁĄCZNIE za przechowanie i przetwarzanie danych faktury
final class Invoice {
    private int id;
    private String seller;
    private String buyer;
    
    private double total;
    
    private DateTimeFormatter dtf;
    
    // GRASP - Creator:
    // Klasa Invoice bezpośrednio używa Item, więc ta klasa tworzy instancję klasy Item
    private Vector <Item> elements;

    

    Invoice (final int id, final String seller, final String buyer) {
        this.id = id;
        this.seller = seller;
        this.buyer = buyer;

        this.total = 0;
        
        this.dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        this.elements = new Vector <Item> ();
    }

    private Boolean isEmpty () {
        return this.elements.size() == 0;
    }

    public Item getElement (final String name) {
        int size = this.elements.size();
        for (int i = 0; i < size; i++) {
            Item elem = this.elements.get(i);
            if (name.equals(elem.getName())) {
                return elem;
            }
        }

        return null;
    }

    public void addItem(final String name, final int quantity, final double price) {
        assert quantity > 0;

        Item elem = this.getElement(name);
        if (elem != null) {
            elem.setQuantity(elem.getQuantity() + quantity);
        }
        else {
            this.elements.add(new Item(name, quantity, price));
        }

        this.total += price * quantity;
    }

    public int removeElement (final String name) {
        Item elem = this.getElement(name);
        if (elem != null) {
            if (this.elements.contains(elem)) {
                int quantitiy = elem.getQuantity();
                this.total -= elem.getPrice() * quantitiy;
                this.elements.remove(elem);
                return quantitiy;
            }

            return -1;
        }
        
        return -1;
    }

    // GRASP - Expert: 
    // Klasa Invoice posiada wszystkie informcacje dot. produktów ich liczby i cen, więc ta klasa liczy wartość całkowitą zakupionych przedmiotów
    public double getTotal () {
        return this.total;
    }

    public void issue () {
        if (this.isEmpty()) {
            System.out.println("The invoice is empty - closed invoice!");
            return;
        }

        String separator = "-------------------------";

        System.out.println("\n\nInvoice #" + id);
        System.out.println(this.dtf.format(LocalDateTime.now()) + "\n");

        System.out.println(this.seller);
        System.out.printf("%25s%n", this.buyer);
        System.out.println(separator);

        // System.out.println("\n" + this.buyer_data);

        int size = this.elements.size();
        for (int i = 0; i < size; i++) {
            Item elem = elements.get(i);
            System.out.println(elem.getName());
            System.out.printf("%14d x %8.2f %n", elem.getQuantity(), elem.getPrice());
        }

        System.out.println(separator);
        System.out.printf("Total: %18.2f %n%n%n", this.total);
    }
}
