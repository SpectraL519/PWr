package com.PWr.app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;





interface DataBaseConnector {
    public Connection connect (String db_url, String username, String password);
    public void update (String column, String new_val, int invoice_id);
    public void insert (int invoice_id, String seller, String buyer, String date);
    public void delete (int invoice_id);
    public void select ();
}





public class InvoiceDataBaseConnector implements DataBaseConnector {
    private Connection connection;

    
    InvoiceDataBaseConnector (String db_url, String username, String password) {
        this.connection = this.connect(db_url, username, password);
    }

    public Connection connect (String db_url, String username, String password) {
        try {
            Connection conn = DriverManager.getConnection(db_url, username, password);
            System.out.println("Succesfully connected to the database!");
            return conn;
        }
        catch (SQLException e) {
            System.out.println("Could not connect to the database!");
            e.printStackTrace();
        }

        return null;
    }

    public void update (String column, String new_val, int invoice_id) {
        // Tutaj zamiast table_name bylaby nazwa tabeli, w ktorej przechowywane sa dane o fakturach w bazie
        String sql = "UPDATE table_name SET ?=? WHERE ID=?";
        try {
            PreparedStatement stmt = this.connection.prepareCall(sql);

            stmt.setString(1, column);
            stmt.setString(2, new_val);
            stmt.setInt(3, invoice_id);

            int rows = stmt.executeUpdate();
            System.out.println("Succesfully updated the database!");
            System.out.println("Rows affected: " + rows);
        } 
        catch (SQLException e ) {
            throw new Error("Encountered a problem while trying to upload to the database: ", e);
        } 
    }

    public void insert (int invoice_id, String seller, String buyer, String date) {
        // Tutaj zamiast table_name bylaby nazwa tabeli, w ktorej przechowywane sa dane o fakturach w bazie
        String sql = "INSER INTO table_name (ID, SELLER, BUYER, DATE) VALUES (?,?,?,?)";
        try {
            PreparedStatement stmt = this.connection.prepareCall(sql);

            stmt.setInt(1, invoice_id);
            stmt.setString(2, seller);
            stmt.setString(3, buyer);
            stmt.setString(4, date);

            int rows = stmt.executeUpdate();
            System.out.println("Succesfully updated the database!");
            System.out.println("Rows affected: " + rows);
        } 
        catch (SQLException e ) {
            throw new Error("Encountered a problem while trying to upload to the database: ", e);
        }
    }

    public void delete (int invoice_id) {
        // Tutaj zamiast table_name bylaby nazwa tabeli, w ktorej przechowywane sa dane o fakturach w bazie
        String sql = "DELETE FROM table_name WHERE ID=?";
        try {
            PreparedStatement stmt = this.connection.prepareCall(sql);

            stmt.setInt(1, invoice_id);

            int rows = stmt.executeUpdate();
            System.out.println("Succesfully updated the database!");
            System.out.println("Rows affected: " + rows);
        } 
        catch (SQLException e ) {
            throw new Error("Encountered a problem while trying to upload to the database: ", e);
        } 
    }

    public void select () {
        // Tutaj zamiast table_name bylaby nazwa tabeli, w ktorej przechowywane sa dane o fakturach w bazie
        String sql = "SELECT * FROM table_name";
        try {
            PreparedStatement stmt = this.connection.prepareCall(sql);         
            
            ResultSet result_set = stmt.executeQuery();

            System.out.println("ID | SELLER | BUYER | DATE");
            while (result_set.next()) {
                int id = result_set.getInt("ID");
                String seller = result_set.getString("SELLER");
                String buyer = result_set.getString("BUYER");
                String date = result_set.getString("DATE");

                System.out.println(id + " | " + seller + " | " + buyer + " | " + date);
            }

            int rows = stmt.executeUpdate();
            System.out.println("Succesfully updated the database!");
            System.out.println("Rows affected: " + rows);
        } 
        catch (SQLException e ) {
            throw new Error("Encountered a problem while trying to upload to the database: ", e);
        } 
    }
}