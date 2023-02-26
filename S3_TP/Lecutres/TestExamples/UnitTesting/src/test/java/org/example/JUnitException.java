package org.example;

import org.junit.Test;


public class JUnitException {

    // Constructor is tested
    
    @Test(expected = IllegalArgumentException.class)
    public void testExpectedException() {
        new Person("Joe", -1);
    }

    @Test
    public void testNonException() {
        new Person("Joe", 3);
    }

}
