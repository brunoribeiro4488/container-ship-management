package lapr.project.data;

import java.sql.*;

public class RouteOfContainer {

    /**
     * Call the function on database that return the route of the container of a client
     * @param databaseConnection The database connection
     * @param clientRegistration The client registration code
     * @param containerNumber The container number
     * @return The route information
     * @throws SQLException
     */
    public String getRouteOfAContainer(DatabaseConnection databaseConnection, String clientRegistration, String containerNumber) throws SQLException {
        String toReturn = "";
        Connection connection = databaseConnection.getConnection();
        String sqlCommand = "{call getRouteOfContainer(?, ?, ?)}";

        CallableStatement getRouteOfAContainerStatement =
                connection.prepareCall(sqlCommand);

        getRouteOfAContainerStatement.setString(1, clientRegistration);
        getRouteOfAContainerStatement.setString(2, containerNumber);
        getRouteOfAContainerStatement.registerOutParameter(3, Types.VARCHAR);

        try {
            getRouteOfAContainerStatement.execute();
        }catch (Exception e){
            return "Invalid client registration code";
        }

        toReturn = getRouteOfAContainerStatement.getString(3);

        getRouteOfAContainerStatement.close();

        return toReturn;
    }
}
