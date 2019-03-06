/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stanley.cuba.java.samples.basicarquilliantest.tests;

import javax.inject.Inject;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import stanley.cuba.java.samples.basicarquilliantest.Adder;

/**
 * This test class will make use of the arquillian suite and test the Adder class in the context of a CDI class
 *
 * @author cubsy
 */

@RunWith(Arquillian.class)
public class TestAdder {
    
    @Deployment
    public static JavaArchive createDeployment() {
        JavaArchive arch = ShrinkWrap.create(JavaArchive.class)
                .addClass(Adder.class)
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println(arch.toString(true));
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        return arch;
        
        
    }
    
    @Inject
    private Adder adder;
    
    @Test
    public void testAdder() {
    
        assertEquals("1 + 2 = 3", adder.add(1, 2));
    
    }

}
