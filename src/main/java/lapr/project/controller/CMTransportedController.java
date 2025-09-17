package lapr.project.controller;

import lapr.project.data.CMTransported;
import lapr.project.data.DatabaseConnection;
import lapr.project.utils.Printer;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Represents the controller that serves as an intermediary between the UI and the domain layer.
 */
public class CMTransportedController {

    /**
     * The controller's cargo manifest transported.
     */
    private final CMTransported cmTransported;

    /**
     * Builds a CMTransportedController without receiving parameters.
     */
    public CMTransportedController() {
        cmTransported = new CMTransported();
    }

    /**
     * calls methods that returns the number of cargo manifests transported and the averag number of containers per cargo manifest.
     *
     * @param databaseConnection
     * @param year
     * @return
     * @throws IOException
     * @throws SQLException
     */
    public boolean cmTransported(DatabaseConnection databaseConnection, String year) throws IOException, SQLException {
        int average = cmTransported.avgContainers(databaseConnection,year);
        int cont = cmTransported.manifestInYear(databaseConnection,year);
        String result = String.format("Number of Cargo Manifests Transported: %d %n Average number of containers per cargo manifest: %d",cont,average);
        return print("CMTransported",result);
    }

    /**
     * Calls the method from class printer which prints the information of the number of cargo manifests transported in a file.
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
        return printer.print(fileName, str,"CMTransported" );
    }

}
