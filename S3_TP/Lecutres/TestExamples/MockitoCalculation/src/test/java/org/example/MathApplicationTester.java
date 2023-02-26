package org.example;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class MathApplicationTester {
    //@InjectMocks annotation is used to create and inject the mock object
    @InjectMocks
    private MathApplication mathApplication = new MathApplication();

    //@Mock annotation is used to create the mock object to be injected
    @Mock
    private CalculatorService calcService;

    @Test
    public void testAdd(){
        //add the behavior of calc service to add two numbers
        when(calcService.add(10.0,20.0)).thenReturn(30.00);

        //test the add functionality
        assertEquals(mathApplication.add(10.0, 20.0),30.0,0);

        //verify that the method with the mock object has been called.
        verify(calcService).add(10.0,20.0);

        //verify that the method with the mock object has been called once.
        verify(calcService, times(1)).add(10.0, 20.0);

        //verify that the method has been executed less than 100 miliseconds.
        verify(calcService, timeout(100)).add(10.0, 20.0);


    }
}
