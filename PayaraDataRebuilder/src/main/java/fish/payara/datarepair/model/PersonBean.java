/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fish.payara.datarepair.model;

import java.io.Serializable;
import java.util.HashMap;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author cubsy
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class PersonBean implements Serializable {
    
    private String id;
    private String fullName;
    private String jobTitle;
    private String dateOfBirth;

    private final HashMap<String, String> appearance;
    private String phoneNumber;
    
    //default No-Args constructor
    public PersonBean() {
        appearance = new HashMap<>();
        id = "Not Given";
        fullName = "Not Given";
        jobTitle = "Not Given";
        dateOfBirth = "Not Given";
        phoneNumber = "Not Given";
    }

    public PersonBean(String id) {
        this();
        this.id = id;
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getAppearance() {
        String myAppearance = "";
        
        myAppearance = appearance.keySet().stream().map((detail) -> "\n\t    " + detail + ": " + appearance.get(detail)).reduce(myAppearance, String::concat);
    
        return myAppearance;
    }

    //Apperance Changing - i.e. Add Detail, Remove Detail, Alter Detail, etc
    public void addAppearanceDetail(String detail, String description) {
        appearance.put(detail, description);
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    
    @Override
    public String toString() {
        String personInfo = "";
        
        personInfo += "Person ID: " + id + " Information: \n";
        personInfo += "\tName: " + fullName + "\n";
        personInfo += "\tJob Title: " + jobTitle + "\n";
        personInfo += "\tDate Of Birth: " + dateOfBirth + "\n";
        personInfo += "\tPhone Number: " + getPhoneNumber() + "\n";
        personInfo += "\tAppearance: " + getAppearance() + "\n";
        
        return personInfo;
    }
    
}
