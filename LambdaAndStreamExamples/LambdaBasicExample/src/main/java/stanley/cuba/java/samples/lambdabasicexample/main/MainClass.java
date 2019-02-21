/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stanley.cuba.java.samples.lambdabasicexample.main;

import java.util.Arrays;
import java.util.List;
import stanley.cuba.java.samples.lambdabasicexample.model.Person;
import java.util.function.*;

/**
 *A class to demonstrate the difference between how things had to be done pre-java SE 8 and how they can be 
 * done now.
 * 
 * @author cubsy
 */
public class MainClass {
    
    public static void main(String[] args) {
        List<Person> people = Arrays.asList(
                new Person("Cuba", "Stanley"),
                new Person("Alan", "Roth"),
                new Person("Matt", "Gill"),
                new Person("Jonathan", "Coustick"),
                new Person("Andrew", "Pielage")
        );
        
        //Step 1: Sort all of the people by their last name
        
            //Using traditional Java 6/7 methods
            
            
            //Using Lambda expressions
        
        //Step 2: Print all people into the console
        
        //Step 3: Print all people who's forename is 4 letters long
        
    }
    
}
