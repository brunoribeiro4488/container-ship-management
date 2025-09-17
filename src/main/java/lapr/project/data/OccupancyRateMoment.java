package lapr.project.data;

import java.sql.*;

public class OccupancyRateMoment {


    /**
     * calculates the occupancy rate of a ship in a specific moment
     *
     * @param databaseConnection
     * @param mmsi
     * @param moment
     * @return
     * @throws SQLException
     */
    public int occupancyRateMoment(DatabaseConnection databaseConnection, String mmsi, String moment) throws SQLException {

        Timestamp ts = Timestamp.valueOf(moment);
        Connection connection = databaseConnection.getConnection();
        String command = "CALL OccupancyRateMOMENT(?, ?, ?)";
        CallableStatement cstmt = connection.prepareCall(command);
        cstmt.setString(1, mmsi);
        cstmt.setTimestamp(2, ts);
        cstmt.registerOutParameter(3, Types.INTEGER);
        cstmt.execute();
        int rate=cstmt.getInt(3);
        cstmt.close();
        return rate;
    }
}
