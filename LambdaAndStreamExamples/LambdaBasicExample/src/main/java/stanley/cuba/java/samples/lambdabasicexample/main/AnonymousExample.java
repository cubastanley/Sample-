/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stanley.cuba.java.samples.lambdabasicexample.main;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import stanley.cuba.java.samples.lambdabasicexample.model.Person;

/**
 *A class to demonstrate the difference between how things had to be done pre-java SE 8 and how they can be 
 * done now.
 * 
 * @author cubsy
 */
public class AnonymousExample {
    
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
            //Creates an anonymuous inner class instance of the Comparator functional interface
            Collections.sort(people, new Comparator<Person>() {
                @Override
                public int compare(Person o1, Person o2) {
                    return o1.getLastName().compareTo(o2.getLastName());
                }
            });
        
        //Step 2: Print all people into the console
        printPeopleList(people);
        
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        
        //Step 3: Print all people who's forename is [x] letters long (without using stream functions)
        //In this example I'll use 4 - makes use of another anonymous inner class statement
        printPeopleBySortCondition(people, new SortCondition() {
            @Override
            public boolean testCondition(Person p) {
                return p.getFirstName().length() == 4;
            }
        });
        
        
        
        
    }
    
    public static void printPeopleList(List<Person> people) {
        for(Person person : people) {
            System.out.println(person.toString());
        }
    }
    
    public static void printPeopleBySortCondition(List<Person> people, SortCondition condition) {
        for(Person person : people) {
            if(condition.testCondition(person)) {
                System.out.println(person.toString());
            }
        }
    }
    
}
