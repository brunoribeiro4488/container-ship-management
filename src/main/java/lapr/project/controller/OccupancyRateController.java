package lapr.project.controller;

import lapr.project.data.DatabaseConnection;
import lapr.project.data.OccupancyRate;
import lapr.project.utils.Printer;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Represents the controller that serves as an intermediary between the UI and the domain layer.
 */
public class OccupancyRateController {

    /**
     * The controller's Occupancy rate.
     */
    private final OccupancyRate occupancyRate;

    /**
     * Builds a OccupancyRateController without receiving parameters.
     */
    public OccupancyRateController() {
        occupancyRate = new OccupancyRate();
    }

    /**
     * calls methods that returns the occupancy rate of a ship.
     *
     * @param databaseConnection
     * @param mmsi
     * @param cargomanifestid
     * @return
     * @throws IOException
     * @throws SQLException
     */
    public boolean occupancyRate(DatabaseConnection databaseConnection, String mmsi,String cargomanifestid) throws IOException, SQLException {
        int rate = occupancyRate.occupancyRate(databaseConnection,mmsi,cargomanifestid);
        String result = "Occupancy rate(percentage): " + rate;
        return print("Occupancy rate",result);
    }

    /**
     * Calls the method from class printer which prints the information of the occupancy rate in a file.
     *
     * @param fileName name of the new file
     *
     * @return true, if the information was saved in a file
     * @return false, if not
     *
     * @throws IOException
     */
    public boolean print(String fileName, String str) throws IOException {
        Printer printer = new Printer();
        return printer.print(fileName, str,"Occupancy rate");
    }
}
