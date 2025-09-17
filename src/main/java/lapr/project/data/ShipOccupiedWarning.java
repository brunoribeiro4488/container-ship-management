package lapr.project.data;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ShipOccupiedWarning {

    /**
     * Get a warning if a ship is occupied
     * @param cargomanifestid The cargo manifest id
     * @param partialid The cargo manifest partial id
     * @param dateload The cargo manifest date load
     * @param done If the cargo manifest is done
     * @param portid The port id
     * @return Warning or success
     * @throws SQLException
     */
    public String insertCargoManifestForShip(String cargomanifestid, String partialid, String dateload, String done, String portid) throws SQLException {
        DatabaseConnection databaseConnection = null;
        try {
            databaseConnection = ConnectionFactory.getInstance()
                    .getDatabaseConnection();
        } catch (IOException exception) {
            Logger.getLogger(ShipStoreDB.class.getName())
                    .log(Level.SEVERE, null, exception);
        }

        Connection connection = databaseConnection.getConnection();
        connection.setAutoCommit(false);

        String command = "INSERT INTO CargoManifestLoad(cargomanifestid, partialid, dateload, done, portid) values(?, ?, ?, ?, ?)";
        CallableStatement cstmt = connection.prepareCall(command);
        cstmt.setString(1, cargomanifestid);
        cstmt.setString(2, partialid);
        Timestamp ts = Timestamp.valueOf(dateload);
        cstmt.setTimestamp(3, ts);
        cstmt.setString(4, done);
        cstmt.setString(5, portid);
        try {
            cstmt.execute();
        }catch (Exception e){
            return "Operation can´t be executed because the ship is already occupied.";
        }
        connection.rollback();
        return "Inserted with success";
    }
}
