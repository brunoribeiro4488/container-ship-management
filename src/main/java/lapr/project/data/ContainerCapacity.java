package lapr.project.data;

import java.sql.*;

public class ContainerCapacity {

    /**
     * Call the function on database that return if a ship can't handle anymore containers
     * @param databaseConnection The database connection
     * @return The information
     * @throws SQLException
     */
    public String containerCapacity(DatabaseConnection databaseConnection) throws SQLException {
        String toReturn = "";
        Connection connection = databaseConnection.getConnection();
        String sqlCommand = "{INSERT INTO Container_CargoManifestLoad(ContainerNumber, containerisocode, cargomanifestloadid, cargomanifestloadpartialid, containerposition, containerweight, load, clientid) values ( ?,?, ?, ?, ?, ?, ?, ?);}";

        CallableStatement containerCapacityStatement =
                connection.prepareCall(sqlCommand);

        containerCapacityStatement.setString(1,"11546321458");
        containerCapacityStatement.setString(2, "1185");
        containerCapacityStatement.setString(3, "77711");
        containerCapacityStatement.setString(4, "92");
        containerCapacityStatement.setString(5, "(0,0,0)");
        containerCapacityStatement.setString(6, "100");
        containerCapacityStatement.setString(7, "ferro");
        containerCapacityStatement.setString(8, "17896");

        try {
            containerCapacityStatement.execute();
        }catch (Exception e){
            return "Operation can´t be executed because the ship doesn´t have available capacity.";
        }

        containerCapacityStatement.close();

        return toReturn;
    }
}
