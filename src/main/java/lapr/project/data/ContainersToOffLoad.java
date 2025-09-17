package lapr.project.data;

import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.OracleTypes;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ContainersToOffLoad {

    /**
     * Call the function on database that return the containers to load on next port
     * @param databaseConnection The database connection
     * @param shipMMSI The ship MMSI to get the information
     * @return A string with the necessary information
     * @throws SQLException
     */
    public String getContainersToOffLoadOnNextPort(DatabaseConnection databaseConnection, String shipMMSI) throws SQLException {
        StringBuilder str = new StringBuilder();
        ResultSet result = null;
        Connection connection = databaseConnection.getConnection();
        String sqlCommand = "{? = call getoffloadcontainer(?)}";

        CallableStatement getContainersToOffLoadStatement =
                connection.prepareCall(sqlCommand);

        getContainersToOffLoadStatement.registerOutParameter(1, OracleTypes.CURSOR);
        getContainersToOffLoadStatement.setString(2,shipMMSI);
        getContainersToOffLoadStatement.execute();
        result = ((OracleCallableStatement) getContainersToOffLoadStatement).getCursor(1);

        while(result.next()){
            str.append(String.format("Container number = %s | Container ISOCode = %s | Container Position = %s | Container Load = %s | Container type = ", result.getString(1), result.getString(2), result.getString(4), result.getString(5)));
            if(result.getString(3) == null){
                str.append(String.format("Not refrigerated%n%n"));
            }else{
                str.append(String.format("Refrigerated (%sº)%n%n", result.getString(3)));
            }
        }

        getContainersToOffLoadStatement.close();
        return str.toString();
    }
}
