package lapr.project.controller;

import lapr.project.data.ContainersToLoad;
import lapr.project.data.DatabaseConnection;
import lapr.project.utils.Printer;

import java.io.IOException;
import java.sql.SQLException;

public class GetLoadContainersController {

    /**
     * Builds a getLoadContainersController receiving the company as a parameter.
     */
    public GetLoadContainersController(){
        // It is not needed to inicialize anything
    }

    /**
     * Call the method of getContainersToLoad to get the containers needed from database
     * @param shipMMSI Ship MMSI from the ship
     * @return True if the information is correctly write in a file
     */
    public boolean getContainersToLoadOnNextPort(DatabaseConnection databaseConnection,String shipMMSI) throws SQLException, IOException {
        ContainersToLoad cs = new ContainersToLoad();
        String result = cs.getContainersToLoadOnNextPort(databaseConnection, shipMMSI);
        return print(String.format("ContainersToLoadOnShip %s", shipMMSI), result);
    }

    /**
     * Calls the method from class printer which prints the information of the containers to be loaded in a file.
     *
     * @param fileName name of the new file
     *
     * @return true, if the information was saved in a file
     * @return false, if not
     *
     * @throws IOException
     */
    public boolean print(String fileName, String stringToPrint) throws IOException {
        Printer printer = new Printer();
        return printer.print(fileName, stringToPrint,"ContainersToLoadOnNextPort" );
    }
}
