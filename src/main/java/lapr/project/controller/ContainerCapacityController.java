package lapr.project.controller;

import lapr.project.data.*;
import lapr.project.utils.Printer;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ContainerCapacityController {

    public boolean containerCapacity() throws SQLException, IOException {
        ContainerCapacity cp = new ContainerCapacity();
        DatabaseConnection databaseConnection = null;
        try {
            databaseConnection = ConnectionFactory.getInstance()
                    .getDatabaseConnection();
        } catch (IOException exception) {
            Logger.getLogger(ShipStoreDB.class.getName())
                    .log(Level.SEVERE, null, exception);
        }
        String result = cp.containerCapacity(databaseConnection);
        if (result.isEmpty()){
            print("ContainerCapacity", "There is space in the ship");
            return false;
        }else {
            return print("ContainerCapacity", result);
        }
    }

    /**
     * Calls the method from class printer which prints the information.
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
        return printer.print(fileName, stringToPrint,"US308" );
    }
}
