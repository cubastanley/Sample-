/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stanley.cuba.java.samples.customannotations;

import stanley.cuba.java.samples.customannotations.annotations.Guitar;

/**
 *
 * @author cuba
 */
public class SchecterGuitar {
    
    private String model;
    private String colour;
    private boolean hasFloydRose = false;

    public SchecterGuitar(String model, String colour) {
        this.model = model;
        this.colour = colour;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public boolean isHasFloydRose() {
        return hasFloydRose;
    }

    public void setHasFloydRose(boolean hasFloydRose) {
        this.hasFloydRose = hasFloydRose;
    }
    
    
    
}
