package com.PWr.app;





// GRASP - indirection
// Klasa InvoiceIssuer pośredniczy między towarem (klasą Inventory) oraz fakturą (klasą Invoice)
final class InvoiceIssuer {
    private Invoice invoice;
    private int invoice_id;
    private String seller;
    private Inventory inventory;



    InvoiceIssuer () {
        this.invoice = null;
        this.invoice_id = 0;
        this.seller = "Abstract Company Inc.";
        this.inventory = new Inventory();
    }

    public void displayInventory () {
        this.inventory.display();
    }

    // GRASP - Creator:
    // Klasa InvoiceIssuer bezpośrednio używa i posiada dane inicjalizujące do Invoice, więc ta klasa tworzy instancję klasy Invoice
    public void newInvoice (String buyer, String db_url, String username, String password) {
        if (this.invoice != null) {
            System.out.println("Could not create a new invoice. Issue the currently open invoice first!");
            return;
        }

        this.invoice = new Invoice(this.invoice_id, this.seller, buyer, db_url, username, password);
    }

    public void addItem(final String name, final int quantity) {
        if (quantity > 0) {
            if (this.invoice != null) {
                Item item = this.inventory.getItem(name);
                if (item != null) {
                    if (item.getQuantity() >= quantity) {
                        this.inventory.sellItem(name, quantity);
                        this.invoice.addItem(name, quantity, item.getPrice());
                    }
                    else { System.out.println("Could not add the given item in quantity of " + quantity + " - not enough in stock!"); }
                }
                else { System.out.println("Could not add the given item - not offered by the store!"); }
            }
            else { System.out.println("Could not add the given item - open an invoice first!"); }
        }
        else { System.out.println("Could not add the given item in quantity of 0!"); }
    }

    public void removeElement(final String name) {
        if (this.invoice != null) {
            int quantity = this.invoice.removeElement(name);
            
            if (quantity == -1) { System.out.println("Could not remove an element that is not on the invoice!"); }
            else { this.inventory.returnItem(name, quantity); }
        }
        else {
            System.out.println("Could not remove the given item - open an invoice first!");
        }
    }

    public void printTotal () {
        if (this.invoice != null) {
            System.out.println(this.invoice.getTotal());
            return;
        }

        System.out.println("Could not get the total cost - open an invoice first!");
    }

    public void issueInvoice () {
        if (this.invoice != null) {
            this.invoice.issue();
            this.invoice = null;
            this.invoice_id += 1;
            return;
        }

        System.out.println("Could not issue an invoice - open an invoice first!");
    }
}
