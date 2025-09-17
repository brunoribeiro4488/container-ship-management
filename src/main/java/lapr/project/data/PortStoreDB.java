package lapr.project.data;

import lapr.project.model.Port;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PortStoreDB implements Persistable {

    @Override
    public boolean save(DatabaseConnection databaseConnection, Object object) {
        Port port = (Port) object;
        boolean returnValue = false;

        try {
            savePortToDatabase(databaseConnection,port);
            //Save changes.
            returnValue = true;

        } catch (SQLException ex) {
            Logger.getLogger(PortStoreDB.class.getName())
                    .log(Level.SEVERE, null, ex);
            databaseConnection.registerError(ex);
            returnValue = false;
        }
        return returnValue;
    }

    //Save Port

    /**
     * Checks is a ShipPosition is already registered on the datase. If the ShipPosition
     * is registered, it does nothing. If it is not, it inserts a new one.
     *
     * @param databaseConnection
     * @param port
     * @throws SQLException
     */
    private void savePortToDatabase(DatabaseConnection databaseConnection,
                                    Port port) throws SQLException {

        if (!isPortOnDatabase(databaseConnection, port)) {
            insertPortOnDatabase(databaseConnection, port);
        }
    }

    /**
     * Checks if a ShipPosition is registered on the Database by its Date and mmsi.
     *
     * @param databaseConnection
     * @param port
     * @return True if the ShipPosition is registered, False if otherwise.
     * @throws SQLException
     */
    private boolean isPortOnDatabase(DatabaseConnection databaseConnection,
                                     Port port) throws SQLException {
        Connection connection = databaseConnection.getConnection();

        boolean isPortOnDatabase = false;

        String sqlCommand = "select * from Port where PortID = ?";

        PreparedStatement getPortPreparedStatement =
                connection.prepareStatement(sqlCommand);

        getPortPreparedStatement.setString(1, port.getId());

        try (ResultSet portResultSet = getPortPreparedStatement.executeQuery()) {

            if (portResultSet.next()) {
                isPortOnDatabase = true;
            } else {
                isPortOnDatabase = false;
            }
        }
        getPortPreparedStatement.close();
        return isPortOnDatabase;
    }

    /**
     * Adds a new ShipPosition record to the database.
     *
     * @param databaseConnection
     * @param port
     * @throws SQLException
     */
    private void insertPortOnDatabase(DatabaseConnection databaseConnection,
                                      Port port) throws SQLException {
        String sqlCommand =
                "INSERT INTO Port(PortID,PortName,CountryName,Latitude,Longitude) values (?, ?, ?, ?, ?)";
        executePortStatementOnDatabase(databaseConnection, port,
                sqlCommand);
    }

    /**
     * Executes the save ShipPosition Statement.
     *
     * @param databaseConnection
     * @param port
     * @throws SQLException
     */
    private void executePortStatementOnDatabase(
            DatabaseConnection databaseConnection,
            Port port, String sqlCommand) throws SQLException {
        Connection connection = databaseConnection.getConnection();

        PreparedStatement savePortPreparedStatement =
                connection.prepareStatement(
                        sqlCommand);
        savePortPreparedStatement.setString(1, port.getId());
        savePortPreparedStatement.setString(2, port.getName());
        savePortPreparedStatement.setString(3, port.getCountry());
        savePortPreparedStatement.setString(4, port.getLatitude());
        savePortPreparedStatement.setString(5, port.getLongitude());
        savePortPreparedStatement.executeUpdate();
        savePortPreparedStatement.close();
    }

    public ArrayList<Port> readPortsfromDB(DatabaseConnection databaseConnection) throws SQLException {
        Port port=null;
        Connection connection = databaseConnection.getConnection();

        String sqlCommand = "select * from PORT";

        PreparedStatement getPortPreparedStatement =
                connection.prepareStatement(sqlCommand);

        String sqlCommandCountry = "select Continent from COUNTRY where COUNTRY=?";

        PreparedStatement getCountryPreparedStatement =
                connection.prepareStatement(sqlCommandCountry);

        ArrayList<Port> portList = new ArrayList<>();

        try (ResultSet portResultSet = getPortPreparedStatement.executeQuery()) {

                while (portResultSet.next()) {
                    getCountryPreparedStatement.setString(1,portResultSet.getString(3));
                    try(ResultSet countryResultSet = getCountryPreparedStatement.executeQuery()) {
                        while (countryResultSet.next()) {
                            port = new Port(portResultSet.getString(1), portResultSet.getString(2), countryResultSet.getString(1), portResultSet.getString(3), portResultSet.getString(4), portResultSet.getString(5));
                            portList.add(port);
                        }
                    }
                }
        }
        getPortPreparedStatement.close();
        return portList;
    }
}