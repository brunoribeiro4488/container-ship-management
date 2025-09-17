package lapr.project.controller;

import lapr.project.data.ConnectionFactory;
import lapr.project.data.DatabaseConnection;
import lapr.project.data.RouteOfContainer;
import lapr.project.data.ShipStoreDB;
import lapr.project.utils.Printer;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GetRouteOfAContainerController {


    public boolean getRouteOfAContainer(String clientRegistration, String containerNumber) throws SQLException, IOException {
        RouteOfContainer rc = new RouteOfContainer();
        DatabaseConnection databaseConnection = null;
        try {
            databaseConnection = ConnectionFactory.getInstance()
                    .getDatabaseConnection();
        } catch (IOException exception) {
            Logger.getLogger(ShipStoreDB.class.getName())
                    .log(Level.SEVERE, null, exception);
        }
        String result = rc.getRouteOfAContainer(databaseConnection, clientRegistration, containerNumber);
        if (result.contains("Invalid")){
            print("RouteOfContainer", result);
            return false;
        }else {
            return print("RouteOfContainer", result);
        }
    }

    /**
     * Calls the method from class printer which prints the information of the route of the container.
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
        return printer.print(fileName, stringToPrint,"US305" );
    }
}
