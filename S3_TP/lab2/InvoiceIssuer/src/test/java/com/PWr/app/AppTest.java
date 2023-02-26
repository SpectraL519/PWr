package com.PWr.app;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import junit.framework.TestCase;

import java.util.Vector;





public class AppTest extends TestCase
{
    protected Invoice testInvoice;
    protected Vector <Item> elements;
    protected double total;
    
    public void setUp () {
        System.out.println("Setting Up");

        this.testInvoice = new Invoice(0, "Abstract Company Inc.", "Test buyer");

        this.elements = new Vector <Item> ();
        this.elements.add(new Item("Bike", 1, 10000.00));
        this.elements.add(new Item("Tube", 3, 10.00));
        this.elements.add(new Item("Tyre", 2, 80.00));
        this.elements.add(new Item("Multitool", 1, 130.00));

        this.total = 0.0;
        for (int i = 0; i < this.elements.size(); i++) {
            Item elem = this.elements.get(i);
            this.total += elem.getQuantity() * elem.getPrice();
            this.testInvoice.addItem(elem.getName(), elem.getQuantity(), elem.getPrice());
        }
    }

    @Test
    public void testTotal () {
        System.out.println("No of Test Case: "+ this.countTestCases());
        System.out.println("Test name: "+ this.getName());

        assertTrue(this.total == this.testInvoice.getTotal());
        System.out.println("Test status: success");
    }

    public void tearDown () {
        System.out.println("Tearing Down");
    }
}