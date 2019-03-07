/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stanley.cuba.java.samples.jsfcdiexample.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author cubsy
 */

@ManagedBean(name = "person")
public class PersonBean {
    
    private String name;
    private String company;
    private String[] favouriteProgrammingLanguages;
    
    public PersonBean() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String[] getFavouriteProgrammingLanguages() {
        return favouriteProgrammingLanguages;
    }

    public void setFavouriteProgrammingLanguages(String[] favouriteProgrammingLanguages) {
        this.favouriteProgrammingLanguages = favouriteProgrammingLanguages;
    }
    
    
    
}
