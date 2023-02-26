// To run the project execute: java -cp target/InvoiceIssuer-1.0-SNAPSHOT.jar com.PWr.app.App
package com.PWr.app;





final class App {
    // GRASP - Creator:
    // Klasa App bezpośrednio używa CommandLine, więc ta klasa tworzy instancję klasy CommandLine
    public static void main(final String[] args) {
        System.out.println("\nBike shop: Invoice Issuer\n");

        CommandLine cmd = new CommandLine();
        
        while (true) {
            try {
                cmd.getCommand();
            }
            catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
