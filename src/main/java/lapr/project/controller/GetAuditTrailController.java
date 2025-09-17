package lapr.project.controller;

import lapr.project.data.*;
import lapr.project.utils.Mappers.AuditTrailMapper;
import lapr.project.utils.Printer;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GetAuditTrailController {

    /**
     * Builds a getAuditTrailController.
     */
    public GetAuditTrailController(){
        // It is not needed to inicialize anything
    }

    /**
     * Call the method of AuditTrail to get the operations needed from database
     * @param containerNumber The container number.
     * @param isoCode The container ISO code
     * @param cargoManifestID The cargo manifest identification
     * @return True if the information is correctly write in a file
     */
    public boolean getAuditTrail(String containerNumber, String isoCode, String cargoManifestID) throws SQLException, IOException {
        AuditTrail at = new AuditTrail();
        DatabaseConnection databaseConnection = null;
        try {
            databaseConnection = ConnectionFactory.getInstance()
                    .getDatabaseConnection();
        } catch (IOException exception) {
            Logger.getLogger(ShipStoreDB.class.getName())
                    .log(Level.SEVERE, null, exception);
        }
        List<List<String>> result = at.getAuditTrail(databaseConnection, containerNumber, isoCode, cargoManifestID);
        StringBuilder stringToPrint = new StringBuilder();
        for(List<String> list : result) {
            stringToPrint.append(AuditTrailMapper.toDTO(list).toString());
            stringToPrint.append("\n");
        }
        if(stringToPrint.length() ==0){
            return false;
        }
        return print("AuditTrail", stringToPrint.toString());
    }

    /**
     * Calls the method from class printer which prints the information of the audit trail.
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
        return printer.print(fileName, stringToPrint,"US304" );
    }
}


