/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stanley.cuba.java.samples.lambdabasicexample.main;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import stanley.cuba.java.samples.lambdabasicexample.model.Person;

/**
 *
 * @author cubsy
 */
public class LambdaExample {
    
    public static void main(String[] args) {
        List<Person> people = Arrays.asList(
                new Person("Cuba", "Stanley"),
                new Person("Alan", "Roth"),
                new Person("Matt", "Gill"),
                new Person("Jonathan", "Coustick"),
                new Person("Andrew", "Pielage")
        );
        
        //Step 1: Sort all of the people by their last name
            
            //Using Lambda expressions
            //A more readable and much neater approach using a lambda expression to remove the need for an
            //anonymous inner class
            Collections.sort(people, (o1, o2) -> o1.getLastName().compareTo(o2.getLastName()));
            //Note: this removes the requirement for importing the Comparator interface - the compiler can just
            //           interperate that this is what I want to do
        
        //Step 2: Print all people into the console
        printPeopleList(people);
        
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        
        //Step 3: Print all people who's forename is [x] letters long (without using stream functions)
        //In this example I'll use 4 - like before, much neater and easier to read
        printPeopleBySortCondition(people, p -> p.getFirstName().length() == 4);
        
    }
    
    //The enhanced forloop can also be written in a lambda expression
    //This is beneficial when making use of multithreading and starts to move more over into the world of streams
    //which will be covered in more detail in another example.
    //The benefit comes with handing over how this function is handled to the computer and so it can decide
    //how it goes about performing the given function on every item in a list
    public static void printPeopleList(List<Person> people) {
        people.forEach(person -> System.out.println(person.toString()));
    }
    
    
    //This method would explicitly make use of streams and so I'll leave it as is for the sakes of consistency
    public static void printPeopleBySortCondition(List<Person> people, SortCondition condition) {
        for(Person person : people) {
            if(condition.testCondition(person)) {
                System.out.println(person.toString());
            }
        }
    }
}
