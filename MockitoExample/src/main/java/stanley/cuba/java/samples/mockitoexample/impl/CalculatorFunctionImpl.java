/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stanley.cuba.java.samples.mockitoexample.impl;

import stanley.cuba.java.samples.mockitoexample.interfaces.CalculatorFunction;

/**
 * 
 * A class representing an implementation that makes use of the external api
 * CalculatorFunction interface and applies extra steps to the calculate method
 * call
 *
 * @author Cuba Stanley
 */
public class CalculatorFunctionImpl {
    
    CalculatorFunction function;

    public CalculatorFunctionImpl(CalculatorFunction function) {
        this.function = function;
    }
    
    public int process(int a, int b) {
        return function.calculate(a, b) * a;
    }
    
}
