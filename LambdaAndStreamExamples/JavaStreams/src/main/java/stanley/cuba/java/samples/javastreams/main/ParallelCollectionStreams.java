/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stanley.cuba.java.samples.javastreams.main;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.Random;
import java.util.stream.Stream;

/**
 *A class to demonstrate parallel streams
 * 
 * An array of 6 random integers is generated and then each of them will be doubled with a gap of
 * 1s (100ms) between each double operation (in order to actually show an effect) 
 * 
 * @author cubsy
 */
public class ParallelCollectionStreams {
    
    public static void main(String[] args) {
        
        Random rand = new Random();
        
        //Create a new array of 50 random double values
        Integer[] randomNumberArray = Stream.generate(() -> rand.nextInt(10) + 1).limit(6).toArray(Integer[]::new);
        
        Instant beforeSequential = Instant.now();
        int total = Arrays.stream(randomNumberArray)
                .mapToInt(ParallelCollectionStreams::doubleWithWait) //used to double AND get an IntStream
                .sum();
        Instant afterSequential = Instant.now();
        Duration duration = Duration.between(beforeSequential, afterSequential);
        
        System.out.println("Total: " + total);
        System.out.println("Time Taken WITHOUT Parallel Stream: " + duration.toMillis() + "ms");
        
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        
        Instant beforeParallel = Instant.now();
        total = Arrays.stream(randomNumberArray)
                .parallel()
                .mapToInt(ParallelCollectionStreams::doubleWithWait) //used to double AND get an IntStream
                .sum();
        Instant afterParallel = Instant.now();
        duration = Duration.between(beforeParallel, afterParallel);
        
        System.out.println("Total: " + total);
        System.out.println("Time Taken WITH Parallel Stream: " + duration.toMillis() + "ms");
        
    }
    
    //Explicit method made in order to create an explicit Thread sleep
    //With integer arithmatic you need a whole bunch of numbers in order to notice any benefit
    //from using parallel streams - so I've stuck a sleep in there to show that it makes a difference
    //to performance
    public static int doubleWithWait(int n) {
        try {
            Thread.sleep(100);
            System.out.println(Thread.currentThread().getName() + " with Curent Number = "  + n);
        } catch (InterruptedException ex) {
        }
        return n*2;
    } 
    
}
