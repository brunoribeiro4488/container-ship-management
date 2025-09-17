package lapr.project.data;

import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.OracleTypes;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AuditTrail {

    /**
     * Call the function on database that return the audit trail of the container of a cargo manifest
     * @param databaseConnection The database connection
     * @param containerNumber The container number
     * @param isoCode The container ISO code
     * @param cargoManifestID The cargo manifest identification
     * @return A list of operations
     * @throws SQLException
     */
    public List<List<String>> getAuditTrail(DatabaseConnection databaseConnection, String containerNumber, String isoCode, String cargoManifestID) throws SQLException {
        List<List<String>> strings = new ArrayList<>();
        ResultSet result = null;
        Connection connection = databaseConnection.getConnection();
        String sqlCommand = "{? = call getAuditTrail(?, ?, ?)}";

        CallableStatement getAuditTrailStatement =
                connection.prepareCall(sqlCommand);

        getAuditTrailStatement.registerOutParameter(1, OracleTypes.CURSOR);
        getAuditTrailStatement.setString(2,containerNumber);
        getAuditTrailStatement.setString(3,isoCode);
        getAuditTrailStatement.setString(4,cargoManifestID);
        getAuditTrailStatement.execute();
        result = ((OracleCallableStatement) getAuditTrailStatement).getCursor(1);

        while(result.next()){
            List<String> temp = new ArrayList<>();
            temp.add(result.getString(1));
            temp.add(result.getString(2));
            temp.add(result.getString(3));
            temp.add(result.getString(4));
            temp.add(result.getString(5));
            temp.add(result.getString(6));
            temp.add(result.getString(7));
            strings.add(new ArrayList<>(temp));
            temp.clear();
        }

        getAuditTrailStatement.close();

        return strings;
    }
}
