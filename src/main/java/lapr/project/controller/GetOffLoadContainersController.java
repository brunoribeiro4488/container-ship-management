package lapr.project.controller;

import lapr.project.data.*;
import lapr.project.utils.Printer;
import java.io.IOException;
import java.sql.SQLException;

public class GetOffLoadContainersController {

    /**
     * Builds a getOffLoadContainersController receiving the company as a parameter.
     */
    public GetOffLoadContainersController(){
        // It is not needed to inicialize anything
    }

    /**
     * Call the method of getContainersToOffLoad to get the containers needed from database
     * @param shipMMSI Ship MMSI from the ship
     * @return True if the information is correctly write in a file
     */
    public boolean getContainersToOffLoadOnNextPort(DatabaseConnection databaseConnection, String shipMMSI) throws SQLException, IOException {
        ContainersToOffLoad cs = new ContainersToOffLoad();
        String result = cs.getContainersToOffLoadOnNextPort(databaseConnection, shipMMSI);
        if(result.length()==0)
            return false;
        return print(String.format("ContainersToOffLoadOnShip%s", shipMMSI), result);
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
        return printer.print(fileName, stringToPrint,"ContainersToOffLoadOnNextPort" );
    }
}
