/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stanley.cuba.java.samples.javastreams.model;

/**
 *
 * A model class for use in these stream examples
 * 
 * @author cubsy
 */
public class Shape {
 
    private String colour; //I'll say red, green or blue
    private String shapeName; //i.e. square 1, circle 2,  triangle 3, etc

    public Shape(String shapeType, String colour) {
        this.colour = colour;
        this.shapeName = shapeType;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public String getShapeName() {
        return shapeName;
    }

    public void setShapeName(String shapeName) {
        this.shapeName = shapeName;
    }

    @Override
    public String toString() {
        return "Shape: colour = " + colour + ", shapeName = " + shapeName;
    }
    
}
