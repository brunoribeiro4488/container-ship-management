package lapr.project.data;

import java.sql.*;

public class WarehouseRate {

    /**
     * Call the function on database that return a string containing the estimate containers leaving a wareHouse and its ocupation Rate.
     * @param databaseConnection The database connection
     * @return string containing the estimate containers leaving a wareHouse and its ocupation Rate
     * @throws SQLException
     */
    public String warehouseRate(DatabaseConnection databaseConnection) throws SQLException {
        String toReturn = "";
        Connection connection = databaseConnection.getConnection();
        String sqlCommand = "{call WarehouseOR(?)}";

        CallableStatement warehouseRateStatement =
                connection.prepareCall(sqlCommand);

        warehouseRateStatement.registerOutParameter(1, Types.VARCHAR);
        warehouseRateStatement.execute();

        toReturn = warehouseRateStatement.getString(1);

        warehouseRateStatement.close();

        return toReturn;
    }
}
