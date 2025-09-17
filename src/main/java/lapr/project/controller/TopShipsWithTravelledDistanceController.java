package lapr.project.controller;

import lapr.project.model.Company;
import lapr.project.utils.Printer;
import lapr.project.utils.stores.ShipStore;

import java.util.*;

/**
 * Represents the controller that serves as an intermediary between the UI and the domain layer.
 *
 * @author Carlos Rodrigues
 */
public class TopShipsWithTravelledDistanceController {

    /**
     * The controller's Ship Store.
     */
    private final ShipStore shipStore;


    /**
     * Builds a TopShipsWithTravelledDistanceController receiving the company as a parameter.
     */
    public TopShipsWithTravelledDistanceController(Company company) {
        this.shipStore = company.getshipStore();
    }


    /**
     * Calls the method from ship store that returns a list of n ships with the most travelled distance.
     *
     * @param firstDateAndTime beginning date of the interval
     * @param lastDateAndTime ending date of the interval
     * @param numberOfShips number of ships wanted to see
     *
     * @return true, if the information was saved in a file
     * @return false, if not
     */
    public boolean getTopShipsWithTravelledDistance(Date firstDateAndTime, Date lastDateAndTime,int numberOfShips){

        try {
            Printer printer = new Printer();
            printer.print("TopShipsWithTravelledDistance", shipStore.getTopShipsWithTravelledDistance(firstDateAndTime,lastDateAndTime,numberOfShips),"TopShips");
            return true;
        }catch (Exception e){
            return false;
        }
    }

}
