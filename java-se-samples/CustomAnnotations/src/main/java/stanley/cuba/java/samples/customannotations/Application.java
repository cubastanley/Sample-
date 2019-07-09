/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stanley.cuba.java.samples.customannotations;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import stanley.cuba.java.samples.customannotations.annotations.Guitar;

/**
 *
 * @author cuba
 */
public class Application {
    
    @Guitar(stringCount = 7)
    private static SchecterGuitar demonFr = new SchecterGuitar("Demon 7 FR", "Snow White");
    
    public static void main(String[] args) throws NoSuchFieldException {

        System.out.println("Details of the Schecter Guitar:");
        
        Field f = Application.class.getDeclaredField("demonFr");
        Annotation a = f.getAnnotation(Guitar.class);
        Guitar g = (Guitar)a;
        
        System.out.println(" - String count: " + g.stringCount());
        System.out.println(" - Pickups: " + g.pickupType());
        System.out.println(" - Colour: " + demonFr.getColour());
        System.out.println(" - Model: " + demonFr.getModel());
    
    }
    
}
