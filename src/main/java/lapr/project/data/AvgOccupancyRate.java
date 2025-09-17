package lapr.project.data;

import java.sql.*;

public class AvgOccupancyRate {
    /**
     * Call the function on database that return the average of ocupancy rate
     * @param databaseConnection The database connection
     * @return The information
     * @throws SQLException
     */
    public String avgOcuppancyRate(DatabaseConnection databaseConnection, String mmsi, String startdate, String enddate) throws SQLException {
        String result = "The average occupancy rate from the date "+startdate+" to the date "+enddate+" on the ship "+mmsi+" is: ";
        Connection connection = databaseConnection.getConnection();
        String sqlCommand = "{CALL avgOccupancyRate(?,?,?,?)}";

        CallableStatement avgOcuppancyRateStatement =
                connection.prepareCall(sqlCommand);
        avgOcuppancyRateStatement.setString(1,mmsi);
        avgOcuppancyRateStatement.setTimestamp(2, Timestamp.valueOf(startdate));
        avgOcuppancyRateStatement.setTimestamp(3,Timestamp.valueOf(enddate));
        avgOcuppancyRateStatement.registerOutParameter(4, Types.DOUBLE);

        avgOcuppancyRateStatement.execute();

        result=result+avgOcuppancyRateStatement.getDouble(4);

        avgOcuppancyRateStatement.close();

        return result;
    }
}
