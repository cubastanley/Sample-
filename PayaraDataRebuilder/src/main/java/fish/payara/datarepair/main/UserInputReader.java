/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fish.payara.datarepair.main;

import java.util.Scanner;

/**
 *
 * @author cubsy
 */
public class UserInputReader {
    
    private Scanner scanner;
    
    /**
     * The constructor for the InputReader - creates a new instance of the
     * java Scanner object to take input from the console
     */
    public UserInputReader() {
    
        scanner = new Scanner(System.in);
    
    }
    
    /**
     * A method to return the String input from the console
     * 
     * @return String inputLine
     */
    public String getInputString() {
        
        System.out.print("> "); // print prompt
        String inputLine = scanner.nextLine();
        return inputLine;
        
    }
    
    /**
     * A method to return the Integer input from the console
     * 
     * @return int inputLine
     */
    public int getInputInt() {
        
        System.out.print("> "); // print prompt
        int inputLine = scanner.nextInt();
        scanner.nextLine();
        return inputLine;
        
    }

    /**
     * A method to return the scanner object of this class
     */
    public Scanner getScanner() {
    
        return scanner;
    
    }

    
}
