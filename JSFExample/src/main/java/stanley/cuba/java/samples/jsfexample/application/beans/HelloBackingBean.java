/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stanley.cuba.java.samples.jsfexample.application.beans;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author cubsy
 */

@Named
@RequestScoped
public class HelloBackingBean {
    
    public String getHello() {
        return "I'm A JSF Backing Bean - Hello!";
    }
    
}
