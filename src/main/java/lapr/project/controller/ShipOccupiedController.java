package lapr.project.controller;

import lapr.project.data.ShipOccupiedWarning;
import lapr.project.utils.Printer;

import java.io.IOException;
import java.sql.SQLException;

public class ShipOccupiedController {

    private static final String US = "US309";

    /**
     * Get a warning if a ship is occupied
     * @param cargomanifestid The cargo manifest id
     * @param partialid The cargo manifest partial id
     * @param dateload The cargo manifest date load
     * @param done If the cargo manifest is done
     * @param portid The port id
     * @return If the information is printed
     * @throws SQLException
     * @throws IOException
     */
    public boolean insertCargoManifestForShip(String cargomanifestid, String partialid, String dateload, String done, String portid) throws SQLException, IOException {
        ShipOccupiedWarning sw = new ShipOccupiedWarning();
        String result = sw.insertCargoManifestForShip(cargomanifestid, partialid, dateload, done, portid);
        if (result.contains("success")){
            print(US, result);
            return false;
        }else{
            return print(US, result);
        }
    }

    /**
     * Calls the method from class printer which prints the warning
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
        return printer.print(fileName, stringToPrint,US );
    }
}
