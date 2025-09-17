package lapr.project.data;

import java.sql.*;

public class ShipVoyages {
    /**
     * Call the function on database that return the ship voyages
     * @param databaseConnection The database connection
     * @return The information
     * @throws SQLException
     */
    public String shipVoyages(DatabaseConnection databaseConnection, String mmsi) throws SQLException {
        String result;
        Connection connection = databaseConnection.getConnection();
        String sqlCommand = "{CALL shipVoyages(?,?)}";

        CallableStatement shipVoyagesStatement =
                connection.prepareCall(sqlCommand);
        shipVoyagesStatement.setString(1,mmsi);
        shipVoyagesStatement.registerOutParameter(2, Types.VARCHAR);

        shipVoyagesStatement.execute();

        result=shipVoyagesStatement.getString(2);

        shipVoyagesStatement.close();

        return result;
    }
}
