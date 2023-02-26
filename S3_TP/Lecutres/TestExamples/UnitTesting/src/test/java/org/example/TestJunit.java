package org.example;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestJunit {
    @Test
    public void testString() {
        String str = "Junit is working fine";
        assertEquals("Junit is working fine",str);
    }

    @Test
    public void testInteger() {
        int a = 10;
        assertEquals(10,a);
    }
}
