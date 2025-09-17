package lapr.project.data;

import java.sql.*;

public class WarehouseCapacity {
    /**
     * Call the function on database that return if a ship can't handle anymore containers
     * @param databaseConnection The database connection
     * @return The information
     * @throws SQLException
     */
    public String warehouseCapacity(DatabaseConnection databaseConnection,String warehouseid) throws SQLException {
        String toReturn = "";
        Connection connection = databaseConnection.getConnection();
        connection.setAutoCommit(false);
        String sqlCommand = "INSERT INTO CargoManifestUNLoad(cargomanifestid, partialid, dateunload, done, portid,WarehouseID) values ( ?,?, ?, ?, ?, ?)";

        CallableStatement warehouseCapacityStatement =
                connection.prepareCall(sqlCommand);

        warehouseCapacityStatement.setString(1,"11113");
        warehouseCapacityStatement.setString(2, "54");
        warehouseCapacityStatement.setTimestamp(3, Timestamp.valueOf("2021-02-21 00:13:01"));
        warehouseCapacityStatement.setString(4, "1");
        warehouseCapacityStatement.setString(5, "");
        warehouseCapacityStatement.setString(6, warehouseid);
        try {
            warehouseCapacityStatement.execute();
        }catch (Exception e){
            return "Operation can´t be executed because the Warehouse doesn´t have available capacity.";
        }


        connection.rollback();

        return toReturn;
    }
}
