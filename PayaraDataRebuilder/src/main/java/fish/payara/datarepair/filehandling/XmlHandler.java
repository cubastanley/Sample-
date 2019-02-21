/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fish.payara.datarepair.filehandling;

import fish.payara.datarepair.main.PayaraDataManager;
import static fish.payara.datarepair.main.PayaraDataManager.inputReader;
import fish.payara.datarepair.model.PersonBean;
import fish.payara.datarepair.model.PersonMap;
import java.io.File;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/**
 *
 * @author cubsy
 */
public class XmlHandler {

    public static void writeDataToFile(HashMap<String, PersonBean> peopleData) {
        writeDataToFile(peopleData, false);
    }

    public static void writeDataToFile(HashMap<String, PersonBean> peopleData, boolean useVerboseWrite) {

        try {
            Thread printThread = null;
            //Data will write to XML file here
            if (useVerboseWrite) {
                Runnable asyncConsoleLog = () -> {
                    System.out.println("The following data is being written to the file: \n");
                    peopleData.values().forEach((person) -> {
                        System.out.println(person.toString());

                    });
                };

                printThread = new Thread(asyncConsoleLog);
                printThread.start();
                useVerboseWrite = false;
            }

            PersonMap peopleList = new PersonMap();
            peopleList.setPersonMap(peopleData);
            JAXBContext context = JAXBContext.newInstance(PersonMap.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(peopleList, new File("./people-data.xml"));

            if(useVerboseWrite) printThread.join(); // Ensures the promt for continuing doesn't take place before async
                                                                               // print to console is made
            
            System.out.println("All data written successfully - Press Enter to return to menu");
            inputReader.getInputString();
            PayaraDataManager.clearScreen();

        } catch (JAXBException ex) {
            Logger.getLogger(PayaraDataManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(XmlHandler.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static HashMap<String, PersonBean> getPeopleDataFromXmlFile() {
        try {
            JAXBContext context = JAXBContext.newInstance(PersonMap.class);
            PersonMap tempMap;
            Unmarshaller unmarshaller = context.createUnmarshaller();
            tempMap = (PersonMap) unmarshaller.unmarshal(new File("./people-data.xml"));

            return tempMap.getPersonMap();

        } catch (JAXBException e) {
            System.out.println("No data currently held to update - New data will be created");
        }

        return new HashMap<>();
    }

}
