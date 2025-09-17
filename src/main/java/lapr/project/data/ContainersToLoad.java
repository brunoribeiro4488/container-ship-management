package lapr.project.data;

import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.OracleTypes;

import java.sql.*;

public class ContainersToLoad {

    /**
     * Call the function on database that return the containers to load on next port
     * @param databaseConnection The database connection
     * @param shipMMSI The ship MMSI to get the information
     * @return A string with the necessary information
     * @throws SQLException
     */
    public String getContainersToLoadOnNextPort(DatabaseConnection databaseConnection, String shipMMSI) throws SQLException {
        StringBuilder str = new StringBuilder();
        ResultSet result = null;
        Connection connection = databaseConnection.getConnection();
        String sqlCommand = "{? = call getloadcontainer(?)}";

        CallableStatement getContainersToLoadStatement =
                connection.prepareCall(sqlCommand);

        getContainersToLoadStatement.registerOutParameter(1, OracleTypes.CURSOR);
        getContainersToLoadStatement.setString(2,shipMMSI);
        getContainersToLoadStatement.execute();
        result = ((OracleCallableStatement) getContainersToLoadStatement).getCursor(1);

        while(result.next()){
            str.append(String.format("Container number = %s | Container ISOCode = %s | Container Position = %s | Container Load = %s | Container type = ", result.getString(1), result.getString(2), result.getString(4), result.getString(5)));
            if(result.getString(3) == null){
                str.append(String.format("Not refrigerated%n%n"));
            }else{
                str.append(String.format("Refrigerated (%sº)%n%n", result.getString(3)));
            }
        }

        getContainersToLoadStatement.close();
        return str.toString();
    }
}
