package lapr.project.controller;

import lapr.project.model.Company;
import lapr.project.utils.stores.PortStore;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;

/**
 * Represents the controller that serves as an intermediary between the UI and the domain layer.
 */
public class ImportPortController {

        /**
         * The controller's Ship Store.
         */
        private final PortStore portStore;

        /**
         * Builds a ImportFileController receiving the company as a parameter.
         */
        public ImportPortController(Company company) {
            this.portStore = company.getPortStore();
        }

        /**
         * Calls the method from ship store that reads all the information of a file and save it in bst's.
         *
         * @param file the file where is the information to be saved
         * @throws IOException
         */
        public void read(File file) throws IOException , ParseException{
            try{
                portStore.read(file);
            }catch(NullPointerException e){
                throw new NullPointerException("File cant be null");
            }
        }
    }
