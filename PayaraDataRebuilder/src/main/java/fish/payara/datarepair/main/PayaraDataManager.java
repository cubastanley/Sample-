/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fish.payara.datarepair.main;

import fish.payara.datarepair.filehandling.DataRepairAgent;
import fish.payara.datarepair.filehandling.XmlHandler;
import fish.payara.datarepair.model.PersonBean;

import java.util.ArrayList;
import java.util.HashMap;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * A class that will handle the main running of the program - will create the
 * menu screen and process user options as well as giving the user access to all
 * of the requirements that the program is supposed to provide
 *
 * @author cubsy
 */
public class PayaraDataManager {

    public static UserInputReader inputReader;

    private static final Pattern idPattern = Pattern.compile("[0-9]{3}");
    private static final Pattern dataFormatPattern = Pattern.compile(".{1,}:*\\s*[^\\s].*");

    //---------------------------------------- MAIN APPLICATION ---------------------------------------
    public static void main(String[] args) {

        inputReader = new UserInputReader();

        //Init fileDirectories
        DataRepairAgent.setDefaultFileDirectories();

        boolean running = true;
        int userChooseCase;
        System.out.println("Welcome to the Payara Server Data Repair and Management Service");

        //Main application loop
        while (running) {
            System.out.println("----------------------------------------------------------");
            System.out.println("----------------What would you like to do?----------------");
            System.out.println("----------------------------------------------------------");

            System.out.println("1) Repair Broken Data From Text Files");
            System.out.println("2) Repair Broken Data From Text Files - Verbose");
            System.out.println("3) Update A Single Entry");
            System.out.println("4) Update Entries In Bulk");
            System.out.println("5) View Current File Directories");
            System.out.println("6) Exit");
            System.out.println();

            System.out.println("Please make a numerical selection:");

            userChooseCase = inputReader.getInputInt();

            switch (userChooseCase) {

                case 1:
                    //Read broken data from files
                    DataRepairAgent.updateFileDirectories();
                    HashMap<String, PersonBean> fetchedData = DataRepairAgent.collectDataFromFiles();
                    if (fetchedData != null) {
                        XmlHandler.writeDataToFile(fetchedData);
                    }
                    break;
                case 2:
                    //Read broken data from files w/ verbose data print
                    DataRepairAgent.updateFileDirectories();
                    HashMap<String, PersonBean> fetchedDataVerbose = DataRepairAgent.collectDataFromFiles();
                    if (fetchedDataVerbose != null) {
                        XmlHandler.writeDataToFile(fetchedDataVerbose, true);
                    }
                    break;
                case 3:
                    clearScreen();
                    //Update a single entry by ID
                    String idToEdit = "";

                    HashMap<String, PersonBean> people = XmlHandler.getPeopleDataFromXmlFile();

                    //Processes checks for a valid ID input
                    while (idToEdit.isEmpty()) {

                        System.out.println("Please enter the ID of the person you'd like to update");
                        System.out.println("Valid IDs are listed below");

                        people.values().forEach((person) -> {
                            System.out.println("\t ID: " + person.getId() + " | Name: " + person.getFullName());
                        });

                        System.out.println();
                        idToEdit = inputReader.getInputString();
                        Matcher matcher = idPattern.matcher(idToEdit);

                        //If the ID contains a valid format then grab the first three digits
                        if (matcher.matches()) {
                            //If remaining data is invalid, empty the string and output a message to say that the ID was 
                            //invalid
                            if (!people.keySet().contains(idToEdit)) {
                                idToEdit = "";
                                clearScreen();
                                System.out.println("The ID you've entered is invalid, please choose a valid ID from the list");
                            }
                            //If the ID was completely invalid then set to empty and print a message prompting for a valid
                            //ID
                        } else {
                            idToEdit = "";
                            clearScreen();
                            System.out.println("Please choose a valid ID from the list");
                        }

                    }

                    //Only gets called after the loop is broken - goes to the update screen
                    updateEntryById(idToEdit);

                    break;
                case 4:

                    HashMap<String, PersonBean> allPeople = XmlHandler.getPeopleDataFromXmlFile();
                    clearScreen();
                    System.out.println("Please enter the IDs of the people you'd like to update");
                    System.out.println("Valid IDs are listed below");

                    allPeople.values().forEach((person) -> {
                        System.out.println("\t ID: " + person.getId() + " | Name: " + person.getFullName());
                    });

                    System.out.println("To update ALL entries, just type \'done\' to continue");
                    System.out.println();

                    String userInput = "";
                    ArrayList<String> idsToEdit = new ArrayList<>();
                    while (!(userInput = inputReader.getInputString()).equalsIgnoreCase("done")) {
                        Matcher matcher = idPattern.matcher(userInput);

                        //If the ID contains a valid format then grab the first three digits
                        if (matcher.matches()) {
                            //If remaining data is invalid, empty the string and output a message to say that the ID was 
                            //invalid
                            if (allPeople.keySet().contains(userInput)) {
                                if (idsToEdit.contains(userInput)) {
                                    System.out.println("The ID you've entered has already been selected for editing, please choose another");
                                } else {
                                    idsToEdit.add(userInput);
                                    allPeople.remove(userInput);
                                }
                            } else {
                                System.out.println("The ID you've entered is invalid, please choose a valid ID from the list");
                            }
                            //If the ID was completely invalid then set to empty and print a message prompting for a valid
                            //ID
                        } else {
                            System.out.println("Please choose a valid ID from the list");
                        }

                    }

                    if (idsToEdit.isEmpty()) {
                        allPeople.keySet().forEach(id -> idsToEdit.add(id));
                    }

                    updateEntriesInBulk(idsToEdit);

                    break;
                case 5:
                    clearScreen();
                    System.out.println(DataRepairAgent.getFileDirectories());
                    break;
                case 6:
                    running = false;
                    break;

            }

        }
        clearScreen();
        System.exit(0);
    }

    //---------------------------------------- UPDATE FILE DATA ---------------------------------------
    private static void updateEntryById(String idToUpdate) {

        HashMap<String, PersonBean> knownUsers = XmlHandler.getPeopleDataFromXmlFile();

        System.out.println("You are now editing: " + knownUsers.get(idToUpdate).getFullName());

        String userEntry = "";
        while (!userEntry.equalsIgnoreCase("done")) {

            System.out.println("To add a detail, type one of the following options followed by the value you wish it to have");
            System.out.println("(e.g. Name: David | Hair Colour: Red)");
            System.out.println("Valid Options Include - Name: | Phone Number: | Job Title: | DOB: | \'Any AppearanceDetail\':");
            System.out.println("To remove data, simply type the field you wish to empty and leave the value blank");
            System.out.println("(i.e. \'Name: \')");
            System.out.println("Type \'done\' to continue");

            userEntry = inputReader.getInputString();
            if (!userEntry.equalsIgnoreCase("done")) {
                Matcher dataMatcher = dataFormatPattern.matcher(userEntry);
                if (dataMatcher.matches()) {
                    String[] splitData = userEntry.trim().split(":");

                    if (splitData.length == 1) {
                        String field = splitData[0].trim();
                        splitData = new String[2];
                        splitData[0] = field;
                        splitData[1] = "Removed";
                    }

                    if (splitData[0].equalsIgnoreCase("Name")) {
                        knownUsers.get(idToUpdate).setFullName(splitData[1].trim());
                    } else if (splitData[0].equalsIgnoreCase("Job Title")) {
                        knownUsers.get(idToUpdate).setJobTitle(splitData[1].trim());
                    } else if (splitData[0].equalsIgnoreCase("DOB")) {
                        knownUsers.get(idToUpdate).setDateOfBirth(splitData[1].trim());
                    } else if (splitData[0].equalsIgnoreCase("Phone Number")) {
                        knownUsers.get(idToUpdate).setPhoneNumber(splitData[1].trim());
                    } else {
                        knownUsers.get(idToUpdate).addAppearanceDetail(splitData[0].trim(), splitData[1].trim());
                    }

                    clearScreen();

                } else {
                    userEntry = "";
                    clearScreen();
                    System.out.println("The data you have entered is in an invalid format - please follow the conventions "
                            + "listed below");
                }

            }
        }

        System.out.println("Data Updated Successfully");
        XmlHandler.writeDataToFile(knownUsers);

    }

    private static void updateEntriesInBulk(ArrayList<String> updateIdSet) {

        HashMap<String, PersonBean> knownUsers = XmlHandler.getPeopleDataFromXmlFile();

        String userEntry = "";
        while (!userEntry.equalsIgnoreCase("done")) {

            System.out.println("To add a detail, type one of the following options followed by the value you wish it to have");
            System.out.println("(e.g. Name: David | Hair Colour: Red)");
            System.out.println("Valid Options Include - Name: | Phone Number: | Job Title: | DOB: | \'Any AppearanceDetail\':");
            System.out.println("To remove data, simply type the field you wish to empty and leave the value blank");
            System.out.println("(i.e. \'Name: \')");
            System.out.println("Type \'done\' to continue");

            userEntry = inputReader.getInputString();
            if (!userEntry.equalsIgnoreCase("done")) {
                Matcher dataMatcher = dataFormatPattern.matcher(userEntry);
                if (dataMatcher.matches()) {
                    String[] splitData = userEntry.trim().split(":");

                    if (splitData.length == 1) {
                        String field = splitData[0].trim();
                        splitData = new String[2];
                        splitData[0] = field;
                        splitData[1] = "Removed";
                    }

                    //Updates every user entry
                    for (int i = 0; i < updateIdSet.size(); i++) {
                        String idToUpdate = updateIdSet.get(i);
                        if (splitData[0].equalsIgnoreCase("Name")) {
                            knownUsers.get(idToUpdate).setFullName(splitData[1].trim());
                        } else if (splitData[0].equalsIgnoreCase("Job Title")) {
                            knownUsers.get(idToUpdate).setJobTitle(splitData[1].trim());
                        } else if (splitData[0].equalsIgnoreCase("DOB")) {
                            knownUsers.get(idToUpdate).setDateOfBirth(splitData[1].trim());
                        } else if (splitData[0].equalsIgnoreCase("Phone Number")) {
                            knownUsers.get(idToUpdate).setPhoneNumber(splitData[1].trim());
                        } else {
                            knownUsers.get(idToUpdate).addAppearanceDetail(splitData[0].trim(), splitData[1].trim());
                        }
                    }

                    clearScreen();

                } else {
                    userEntry = "";
                    clearScreen();
                    System.out.println("The data you have entered is in an invalid format - please follow the conventions "
                            + "listed below");
                }

            }

        }

        System.out.println("Data Updated Successfully");
        XmlHandler.writeDataToFile(knownUsers);

    }

    //-----------------------------------------MISC METHODS-----------------------------------------
    public static void clearScreen() {

        System.out.print("\033[H\033[2J");
        System.out.flush();

    }

}
