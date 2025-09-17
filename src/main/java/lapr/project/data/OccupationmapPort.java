package lapr.project.data;

import java.sql.*;

public class OccupationmapPort {
    /**
     * Call the function on database that return the position and id for a container of a given manifest
     * @param databaseConnection The database connection
     * @return The information
     * @throws SQLException
     */
    public String occupationmapPort(DatabaseConnection databaseConnection,String portId,String date) throws SQLException {
        String result = "";
        Connection connection = databaseConnection.getConnection();
        String sqlCommand = "{call occupationmapport(?,?,?)}";

        CallableStatement occupationmapportStatement =
                connection.prepareCall(sqlCommand);

        occupationmapportStatement.setTimestamp(1, Timestamp.valueOf(date));
        occupationmapportStatement.setString(2,portId);
        occupationmapportStatement.registerOutParameter(3, Types.VARCHAR);

        occupationmapportStatement.execute();

        result=occupationmapportStatement.getString(3);

        occupationmapportStatement.close();

        return result;
    }
}
