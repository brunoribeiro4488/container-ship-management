package lapr.project.data;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

public class LoadingUnloadingMap {
    /**
     * Call the function on database that return the Loading and Unloading map
     * @param databaseConnection The database connection
     * @return The information
     * @throws SQLException
     */
    public String loadingUnloading(DatabaseConnection databaseConnection, String portid) throws SQLException {
        String result;
        Connection connection = databaseConnection.getConnection();
        String sqlCommand = "{CALL LoadingUnloadingMap(?,?)}";

        CallableStatement loadingUnloadingStatement =
                connection.prepareCall(sqlCommand);
        loadingUnloadingStatement.setString(1,portid);
        loadingUnloadingStatement.registerOutParameter(2, Types.VARCHAR);

        loadingUnloadingStatement.execute();

        result=loadingUnloadingStatement.getString(2);

        loadingUnloadingStatement.close();

        return result;
    }
}
