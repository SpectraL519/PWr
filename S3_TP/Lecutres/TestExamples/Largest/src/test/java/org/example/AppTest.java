package org.example;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    @Test
    public void testLargestNegative() {
        assertEquals(-7, Largest.largest(new int[] {-9,-8,-7,-9}));
    }

    @Test
    public void testLargestPositive() {
        assertEquals(9, Largest.largest(new int[] {9,8,9,7}));
    }
}
