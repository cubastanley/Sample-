/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stanley.cuba.java.samples.customannotations.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *
 * @author cuba
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Guitar {
    
    int stringCount() default 6;
    PickupType pickupType() default PickupType.humbucker;
    
    public enum PickupType {
        humbucker,
        p90,
        doublecoil,
        singlecoil
    }
    
}
