package lapr.project.data;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

public class OccupancyRate {

    /**
     * calculates the occupancy rate of a ship
     *
     * @param databaseConnection
     * @param mmsi
     * @param cargomanifestid
     * @return
     * @throws SQLException
     */
    public int occupancyRate(DatabaseConnection databaseConnection,String mmsi,String cargomanifestid) throws SQLException {

        Connection connection = databaseConnection.getConnection();

        String command = "call  OccupancyRate(?, ?, ?)";
        CallableStatement cstmt = connection.prepareCall(command);
        cstmt.setString(1, mmsi);
        cstmt.setString(2, cargomanifestid);
        cstmt.registerOutParameter(3, Types.INTEGER);
        cstmt.execute();
        int rate=cstmt.getInt(3);
        cstmt.close();
        return rate;
    }



}
