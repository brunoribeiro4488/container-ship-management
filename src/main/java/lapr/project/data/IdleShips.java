package lapr.project.data;

import java.sql.*;

public class IdleShips {

    /**
     * Call the function on database that return the idle ships
     * @param databaseConnection The database connection
     * @return The information
     * @throws SQLException
     */
    public String idleShips(DatabaseConnection databaseConnection) throws SQLException {
        String result = "";
        Connection connection = databaseConnection.getConnection();
        String sqlCommand = "{? = call IdleShips}";

        CallableStatement idleshipsStatement =
                connection.prepareCall(sqlCommand);

        idleshipsStatement.registerOutParameter(1, Types.VARCHAR);

        idleshipsStatement.execute();

        result=idleshipsStatement.getString(1);

        idleshipsStatement.close();

        return result;
    }
}
