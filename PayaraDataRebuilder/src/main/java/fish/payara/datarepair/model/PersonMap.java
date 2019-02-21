/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fish.payara.datarepair.model;

import java.util.ArrayList;
import java.util.HashMap;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author cubsy
 */
@XmlRootElement(name="people")
@XmlAccessorType(XmlAccessType.FIELD)
public class PersonMap {
    
    private HashMap<String, PersonBean> personMap = new HashMap<>();

    public void setPersonMap(HashMap<String, PersonBean> personMap) {
        this.personMap = personMap;
    }

    public HashMap<String, PersonBean> getPersonMap() {
        return personMap;
    }

    
}
