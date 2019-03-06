/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stanley.cuba.java.samples.basicarquilliantest;

/**
 *
 * A really basic class that will take in two numbers and then return a sentence stating the sum of said numbers
 * 
 * @author cubsy
 */
public class Adder {
    
    public String add(int x, int y) {
        int answer = x+y;
        return x + " + " + y + " = " + answer;
    }
    
}
