/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stanley.cuba.java.samples.mockitoexample.interfaces;

/**
 *
 * A very basic interface that will be used to represent an externally made
 * api which is already properly tested by its external developers
 * 
 * @author Cuba Stanley
 */
public interface CalculatorFunction {
    
    public int calculate(int a, int b);
    
}
