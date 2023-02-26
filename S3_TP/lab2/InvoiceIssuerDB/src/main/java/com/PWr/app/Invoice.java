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

    private String db_url;
    
    private double total;
    
    private DateTimeFormatter dtf;
    
    // GRASP - Creator:
    // Klasa Invoice bezpośrednio używa Item, więc ta klasa tworzy instancję klasy Item
    private Vector <Item> elements;
    private InvoiceDataBaseConnector db_connector;

    

    Invoice (final int id, final String seller, final String buyer, final String db_url, final String username, final String password) {
        this.id = id;
        this.seller = seller;
        this.buyer = buyer;

        this.total = 0;
        
        this.dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        this.elements = new Vector <Item> ();

        this.db_url = db_url;
        this.db_connector = new InvoiceDataBaseConnector(this.db_url, username, password);
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
            System.out.println("Could not upload an empty invoice!");
            return;
        }
        
        this.db_connector.insert(this.id, this.seller, this.buyer, dtf.format(LocalDateTime.now()));    
    }
}
