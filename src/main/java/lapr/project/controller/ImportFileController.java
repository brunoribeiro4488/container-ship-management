package lapr.project.controller;

import lapr.project.model.Company;
import lapr.project.utils.stores.ShipStore;

import java.io.*;
import java.text.ParseException;

/**
 * Represents the controller that serves as an intermediary between the UI and the domain layer.
 */
public class ImportFileController {

    /**
     * The controller's Ship Store.
     */
    private final ShipStore shipStore;

    /**
     * Builds a ImportFileController receiving the company as a parameter.
     */
    public ImportFileController(Company company) {
        this.shipStore = company.getshipStore();
    }

    /**
     * Calls the method from ship store that reads all the information of a file and save it in bst's.
     *
     * @param file the file where is the information to be saved
     * @throws IOException
     * @throws ParseException
     */
    public void read(File file) throws IOException, ParseException {
       try{
           shipStore.read(file);
       }catch(NullPointerException e){
           throw new NullPointerException("File cant be null");
       }
    }
}
