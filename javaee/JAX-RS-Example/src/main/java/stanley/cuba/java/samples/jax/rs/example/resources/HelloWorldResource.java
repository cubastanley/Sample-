/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stanley.cuba.java.samples.jax.rs.example.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 *
 * @author cuba
 */

@Path("/")
@Produces("text/html")
public class HelloWorldResource {
    
    @GET
    public String helloWorld() {
        return "<h1> Hello World! </h1>";
    }
    
    @GET
    @Path("{name}")
    public String helloName(@PathParam("name") String name) {
        return "<h1> Hello " + name + "! </h1>";
    }
    
}
