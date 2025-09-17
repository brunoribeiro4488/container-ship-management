package lapr.project.controller;

import lapr.project.data.DatabaseConnection;
import lapr.project.data.OccupancyRateMoment;
import lapr.project.utils.Printer;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Represents the controller that serves as an intermediary between the UI and the domain layer.
 */
public class OccupancyRateMomentController {

    /**
     * The controller's Occupancy rate moment.
     */
    private final OccupancyRateMoment occupancyRateMoment;

    /**
     * Builds a OccupancyRateMomentController without receiving parameters.
     */
    public OccupancyRateMomentController() {
        occupancyRateMoment = new OccupancyRateMoment();
    }

    /**
     * calls methods that returns the occupancy rate of a ship in a specific moment.
     *
     * @param databaseConnection
     * @param mmsi
     * @param moment
     * @return
     * @throws IOException
     * @throws SQLException
     */
    public boolean occupancyRateMoment(DatabaseConnection databaseConnection, String mmsi, String moment) throws IOException, SQLException {
        int rate = occupancyRateMoment.occupancyRateMoment(databaseConnection,mmsi,moment);
        String result = "Occupancy rate at a given moment(percentage): "+rate;
        return print("OccupancyRateMoment",result);
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
        return printer.print(fileName, str,"OccupancyRateMoment" );
    }
}
