package lapr.project.controller;

import lapr.project.data.*;
import lapr.project.model.Company;
import lapr.project.utils.Printer;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ShipsAvailableController {

    /**
     * Builds a ShipsAvailableController.
     */
    public ShipsAvailableController(Company company){
    }


    /**
     * Call the method of ShipAvailable to get the ships needed from database
     * @return True if the information is correctly write in a file
     */
    public boolean getShipsAvailable() throws SQLException, IOException {
        ShipAvailable sa = new ShipAvailable();
        DatabaseConnection databaseConnection = null;
        try {
            databaseConnection = ConnectionFactory.getInstance()
                    .getDatabaseConnection();
        } catch (IOException exception) {
            Logger.getLogger(ShipStoreDB.class.getName())
                    .log(Level.SEVERE, null, exception);
        }
        String result = sa.getShipsAvailable(databaseConnection);
        return print("Ships Available", result);
    }

    /**
     * Calls the method from class printer which prints the information of the ships available in a file.
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
        return printer.print(fileName, stringToPrint,"ShipsAvailable");
    }
}
