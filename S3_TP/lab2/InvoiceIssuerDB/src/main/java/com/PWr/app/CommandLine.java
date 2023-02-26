package com.PWr.app;

import java.util.Scanner;





// GRASP - Cohesion:
// Klasa CommandLine odpowiada WYŁĄCZNIE za przetwarzanie komend czytanych ze strumienia wejściowego
final class CommandLine {
    private Scanner std_in_scanner;
    private InvoiceIssuer issuer;

    private String command;
    private String item_name;
    private int quantity;
    private String db_url;


    // GRASP - Creator:
    // Klasa CommandLine bezpośrednio używa InvoiceIssuer, więc ta klasa tworzy instancję klasy InvoiceIssuer
    CommandLine () {
        std_in_scanner = new Scanner(System.in);
        this.issuer = new InvoiceIssuer();

        this.db_url = null;
    }

    public void getCommand () {
        System.out.print("cmd: ");

        this.command = std_in_scanner.next();
        
        switch (this.command) {
            case "help":
                System.out.println("\nInvoice Issuer program commands:");
                System.out.println("\t- displayInventory : Displays the current inventory");
                System.out.println("\t- openInvoice <database_url> <username> <password> \"buyer data\": Opens a new invoice which will be upladed to <database_url> database\n\t\tIf <database_url> = '-p' the URL will be set to the previously used URL");
                System.out.println("\t- addItem <item_name> <quantitiy> : Adds <item_name> to the currently open invoice in quantity of <quantity>");
                System.out.println("\t- removeElement <element_name> : Removes element <element_name> from the currrently open invoice");
                System.out.println("\t- total : Prints out the total cost of the currently open invoice");
                System.out.println("\t- issueInvoice : Issues the currently open invoice");
                System.out.println("\t- exit : Exits the program\n");
                break;

            case "displayInventory":
                System.out.println("\nInventory:\n");
                this.issuer.displayInventory();
                break;

            case "openInvoice":
                String url = this.std_in_scanner.next();
                String username = this.std_in_scanner.next();
                String password = this.std_in_scanner.next();

                if (this.db_url == null && url == "-p") {
                    System.out.println("Could not open a new invoice - no database specified!");
                }
                else {
                    this.db_url = url;

                    String buyer_data = this.std_in_scanner.nextLine();
                    buyer_data = buyer_data.trim().substring(1, buyer_data.length() - 2);
                    
                    this.issuer.newInvoice(buyer_data, this.db_url, username, password);
                }
                break;

            case "addItem":
                this.item_name = std_in_scanner.next();
                this.quantity = std_in_scanner.nextInt();
                this.issuer.addItem(this.item_name, this.quantity);
                break;

            case "removeElement":
                this.item_name = std_in_scanner.next();
                this.issuer.removeElement(this.item_name);
                break;

            case "total":
                this.issuer.printTotal();
                break;

            case "issueInvoice":
                this.issuer.issueInvoice();
                break;
            
            case "exit":
                System.exit(0);

            default:
                System.out.println("Invalid command!");
                std_in_scanner.nextLine();
                System.out.println("To get a commands' overview type 'help'");
        }
    }
}
