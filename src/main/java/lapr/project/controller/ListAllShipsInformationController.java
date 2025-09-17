package lapr.project.controller;

import lapr.project.model.Company;
import lapr.project.utils.Printer;
import lapr.project.utils.stores.ShipStore;

import java.io.IOException;

/**
 * Represents the controller that serves as an intermediary between the UI and the domain layer.
 *
 * @author Bruno Ribeiro
 */
public class ListAllShipsInformationController {

    /**
     * The controller's Ship Store.
     */
    ShipStore store;

    /**
     * Builds a ListAllShipsInformationController receiving the company as a parameter.
     */
    public ListAllShipsInformationController(Company company){
        store = company.getshipStore();
    }

    /**
     * Calls the methods from ship store where gets all travelled distance and number of movements of each ship. Finally, prints everything in a file.
     *
     * @return true, if the information was saved in a file
     * @return false, if not
     */
    public boolean getInformationAllShips(){
        try {
            String string = "Order by travelled distance (descending)\n\n";
            string += store.getAllShipsInformationTravelledDistance();
            string += "\n\nOrder by number of movements (ascending)\n\n";
            string += store.getAllShipsInformationNumberMovements();
            return print("ShipsInformation", string);
        }catch (Exception e){
            return false;
        }
    }

    /**
     * Calls the method from class printer which prints a string in a file.
     *
     * @param fileName name of the new file
     * @param str information to be printed
     *
     * @return true, if the information was saved in a file
     * @return false, if not
     *
     * @throws IOException
     */
    public boolean print(String fileName, String str) throws IOException {
        Printer printer = new Printer();
        return printer.print(fileName, str,"AllShipsInformation" );
    }
}
