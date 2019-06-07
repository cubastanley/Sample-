/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stanley.cuba.java.samples.mockitoexample;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import stanley.cuba.java.samples.mockitoexample.impl.CalculatorFunctionImpl;
import stanley.cuba.java.samples.mockitoexample.interfaces.CalculatorFunction;

/**
 *
 * @author Cuba Stanley
 */
public class CalculatorFunctionTest {

    CalculatorFunctionImpl calc = null;
    
    @Mock
    CalculatorFunction calcFunc; //Treating this as an external api that's already been properly tested
    
    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();
    
    @Before
    public void setUp() {
        calc = new CalculatorFunctionImpl(calcFunc);
    }
    
    @Test
    /**
     * Used to test the CalculatorFunctionImpl.process(a, b) method
     * uses a mock implementation of CalculatorFunction as it has a dependency
     * on this object but we don't want to be testing that object in this test
     */
    public void testCalculatorProcess() {
    
        //Makes it so that when the method calculate(2, 3) is called on
        //the calcFunc interface, without implementation, it returns a value
        //of 5
        when(calcFunc.calculate(2, 3)).thenReturn(5);
        
        assertEquals(10, calc.process(2, 3));
        
        //This line verifies that the method was actually called on the mock object
        //rather that it being a hard coded process used instead
        verify(calcFunc.calculate(2, 3));
    
    }
}
