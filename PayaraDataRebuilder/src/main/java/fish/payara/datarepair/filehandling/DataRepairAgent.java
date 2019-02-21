/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fish.payara.datarepair.filehandling;

import fish.payara.datarepair.main.PayaraDataManager;
import static fish.payara.datarepair.main.PayaraDataManager.clearScreen;
import static fish.payara.datarepair.main.PayaraDataManager.inputReader;
import fish.payara.datarepair.model.PersonBean;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author cubsy
 */
public class DataRepairAgent {

    private static String[] fileDirectories;

    public static void setDefaultFileDirectories() {
        if(fileDirectories == null) {
            fileDirectories = new String[3];
        }
        for (int i = 0; i < fileDirectories.length; i++) {
            fileDirectories[i] = "/home/cubsy/Documents/PayaraDataFiles/File-" + (i + 1) + ".txt"; //Sets the default directory for each file
        }
    }

    public static void updateFileDirectories() {
        clearScreen();
        for (int i = 0; i < fileDirectories.length; i++) {

            System.out.println("Please Enter The Directory For File " + (i + 1));
            System.out.println("(If you wish to use the default directory, just press enter)");
            System.out.println();

            String newDirectory = inputReader.getInputString();
            if (newDirectory.isEmpty()) {

                System.out.println("---------- Using default directory for file " + (i + 1) + " ----------");
                System.out.println();

            } else {
                fileDirectories[i] = newDirectory;
                System.out.println("---------- Using directory - " + newDirectory + " - for file " + (i + 1) + " ----------");
                System.out.println();
            }
        }
    }

    public static String getFileDirectories() {
        String output = "";
        for (int i = 0; i < fileDirectories.length; i++) {
            output += ("File " + (i + 1) + ": \'" + fileDirectories[i] + "\'\n");
        }
        return output;
    }

    public static HashMap<String, PersonBean> collectDataFromFiles() {

        ArrayList<String> allFileData = new ArrayList<>();

        for (String fileDirectory : fileDirectories) {

            System.out.println("Trying to read file at directory \'" + fileDirectory + "\'");

            try {

                BufferedReader reader = new BufferedReader(new FileReader(fileDirectory));
                String line;
                while ((line = reader.readLine()) != null) {
                    allFileData.add(line);
                }
                reader.close();

            } catch (FileNotFoundException e) {

                System.out.println("The file at directory \'" + fileDirectory + "\' could not be found - skipping file");

            } catch (IOException ex) {
                Logger.getLogger(PayaraDataManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        allFileData.forEach((line) -> {
            System.out.println(line);
        });

        if (allFileData.isEmpty()) {
            System.out.println("No data could be obtained from the given file directories");
            System.out.println("- Check the files to ensure they aren't empty -");
            return null;
        }
            System.out.println("Reading complete - Repairing files");
            return(sortAndBuildData(allFileData));
    }

    public static HashMap<String, PersonBean> sortAndBuildData(ArrayList<String> data) {

        HashMap<String, String> dataByID = new HashMap<>(); //Will map an entry ID to information about
        //a person
        Pattern idPattern = Pattern.compile("ID:.[0-9]{3},");

        //For every id, add new data from that line into the hash map position with that id - shouldn't lose any data
        data.forEach((line) -> {
            Matcher match = idPattern.matcher(line);
            if (match.find()) {
                String mapId = match.group().replaceAll("\\s+", "");
                mapId = mapId.split(":")[1].replace(',', ' ');

                String curData = dataByID.get(mapId);
                curData = ((curData == null) ? line.replaceAll(match.group(), "") : curData + line.replaceAll(match.group(), "")).trim();
                curData += ","; //Ensures all data is separated by a comma
                dataByID.put(mapId, curData);
            }
        });

        //Build Data into a person object
        HashMap<String, PersonBean> people = XmlHandler.getPeopleDataFromXmlFile();

        for (String id : dataByID.keySet()) {
            PersonBean person = (people.get(id.trim()) == null) ? new PersonBean(id.trim()) : people.get(id.trim());
            Scanner dataScanner = new Scanner(dataByID.get(id));
            dataScanner.useDelimiter(",");
            while (dataScanner.hasNext()) {
                String nextData = dataScanner.next().trim();

                String[] splitData = nextData.split(":");
                if (splitData[0].equals("Appearance")) {

                    String appearanceData = "";
                    for (int i = 1; i < splitData.length; i++) {
                        if (i != splitData.length - 1) {
                            appearanceData += splitData[i] + ":";
                        } else {
                            appearanceData += splitData[i];
                        }
                    }

                    //Sort through and assign each detail of appearance to the person
                    Scanner appearanceDetails = new Scanner(appearanceData.trim());
                    appearanceDetails.useDelimiter(";");
                    while (appearanceDetails.hasNext()) {
                        String[] appearance = appearanceDetails.next().trim().split(":");
                        person.addAppearanceDetail(appearance[0].trim(), appearance[1].trim());
                    }

                } else {
                    //Otherwise, assign respective value into the person object
                    switch (splitData[0]) {
                        case ("Name"):
                            person.setFullName(splitData[1].trim());
                            break;
                        case ("Job Title"):
                            person.setJobTitle(splitData[1].trim());
                            break;
                        case ("DOB"):
                            person.setDateOfBirth(splitData[1].trim());
                            break;
                        case ("Phone Number"):
                            person.setPhoneNumber(splitData[1].trim());
                            break;
                    }
                }
            }

            people.put(person.getId(), person);
        }
        clearScreen();

        return people;

    }

}
