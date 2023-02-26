package com.PWr.app;

import junit.framework.TestCase;
import org.junit.Test;





public class TotalTest extends TestCase  {
    protected double fValue1;
    protected double fValue2;

    public void setUp() {
        fValue1 = 2.0;
        fValue2 = 3.0;
        System.out.println("Setting Up");
    }

    @Test
    public void testAdd() {
        //count the number of test cases
        System.out.println("No of Test Case = "+ this.countTestCases());

        System.out.println("Value1 = "+ this.fValue1);
        System.out.println("Value2 = "+ this.fValue2);

        //test getName
        String name = this.getName();
        System.out.println("Test Case Name = "+ name);

        //test setName
        this.setName("testNewAdd");
        String newName = this.getName();
        System.out.println("Updated Test Case Name = "+ newName);
    }

    //tearDown used to close the connection or clean up activities
    public void tearDown( ) {
        System.out.println("Tearing Down");
    }
}
