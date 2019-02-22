/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stanley.cuba.java.samples.javastreams.main;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import stanley.cuba.java.samples.javastreams.model.Shape;

/**
 *This class will demonstrate some basic stream functions on a collection of shapes 
 * 
 * @author cubsy
 */
public class BasicCollectionsStreaming {
    
    public static void main(String[] args) {
        
        List<Shape> shapes = Arrays.asList(
                new Shape("square 1", "red"),
                new Shape("circle 1", "red"),
                new Shape("triangle 1", "red"),
                
                new Shape("square 2", "green"),
                new Shape("circle 2", "green"),
                new Shape("triangle 2", "green"),
                
                new Shape("square 3", "blue"),
                new Shape("circle 3", "blue"),
                new Shape("triangle 3", "blue")
        );
        
        //Print all shapes which are coloured blue
            //Converts the shapes list into a stream, filters out all Shape objects in the list and the prints them all to 
            //the console using a method reference
        shapes.stream().
                filter(shape -> shape.getColour().equals("blue"))
                .forEach(System.out::println);
        
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        
        //Print the colours of each square
        shapes.stream()
                .filter(shape -> shape.getShapeName().contains("square"))
                .forEach(shape -> System.out.println(shape.getColour()));
        
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        
        //Get the number of red shapes
        System.out.println("Number of red shapes: " + shapes.stream()
                .filter(shape -> shape.getColour().equals("red"))
                .count());
        
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        
        //Make all squares yellow
        shapes.stream()
                .filter(shape -> shape.getShapeName().contains("square"))
                .forEach(shape -> {
                    shape.setColour("yellow");
                    System.out.println(shape.toString());
                });
        
    }
    
}
