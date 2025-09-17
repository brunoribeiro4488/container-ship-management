package lapr.project.data;

import lapr.project.model.Ship;
import lapr.project.model.ShipPosition;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ShipStoreDB implements Persistable {

    @Override
    public boolean save(DatabaseConnection databaseConnection, Object object) {
        Ship ship = (Ship) object;
        boolean returnValue = false;

        try {
            saveShipToDatabase(databaseConnection, ship);
            for(ShipPosition sp :ship.getSp().inOrder()) {
                saveShipPositionToDatabase(databaseConnection,sp);
            }
            //Save changes.
            returnValue = true;

        } catch (SQLException ex) {
            Logger.getLogger(ShipStoreDB.class.getName())
                    .log(Level.SEVERE, null, ex);
            databaseConnection.registerError(ex);
            returnValue = false;
        }
        return returnValue;
    }

    //Save Ship

    /**
     * Checks if a ship is already registered on the datase. If the ship
     * is registered, it does nothing. If it is not, it inserts a new one.
     *
     * @param databaseConnection
     * @param ship
     * @throws SQLException
     */
    private void saveShipToDatabase(DatabaseConnection databaseConnection,
                                    Ship ship) throws SQLException {

        if (!isShipOnDatabase(databaseConnection, ship)) {
            insertShipOnDatabase(databaseConnection, ship);
        }
    }

    /**
     * Checks if a ship is registered on the Database by its mmsi.
     *
     * @param databaseConnection
     * @param ship
     * @return True if the ship is registered, False if otherwise.
     * @throws SQLException
     */
    private boolean isShipOnDatabase(DatabaseConnection databaseConnection,
                                     Ship ship) throws SQLException {
        Connection connection = databaseConnection.getConnection();

        boolean isShipOnDatabase = false;

        String sqlCommand = "select * from SHIP where mmsi = ?";

        PreparedStatement getShipPreparedStatement =
                connection.prepareStatement(sqlCommand);

        getShipPreparedStatement.setString(1, ship.getMmsi());

        try (ResultSet shipResultSet = getShipPreparedStatement.executeQuery()) {

            if (shipResultSet.next()) {
                isShipOnDatabase = true;
            } else {
                isShipOnDatabase = false;
            }
        }
        getShipPreparedStatement.close();
        return isShipOnDatabase;
    }

    /**
     * Adds a new ship record to the database.
     *
     * @param databaseConnection
     * @param ship
     * @throws SQLException
     */
    private void insertShipOnDatabase(DatabaseConnection databaseConnection,
                                      Ship ship) throws SQLException {
        String sqlCommand =
                "INSERT INTO Ship(MMSI,IMO,CallSign,VesselName,VesselType,Shiplength,Width,Draft,Capacity) values (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        executeShipStatementOnDatabase(databaseConnection, ship,
                sqlCommand);
    }

    /**
     * Executes the save Ship Statement.
     *
     * @param databaseConnection
     * @param ship
     * @throws SQLException
     */
    private void executeShipStatementOnDatabase(
            DatabaseConnection databaseConnection,
            Ship ship, String sqlCommand) throws SQLException {
        Connection connection = databaseConnection.getConnection();

        PreparedStatement saveShipPreparedStatement =
                connection.prepareStatement(
                        sqlCommand);
        saveShipPreparedStatement.setString(1, ship.getMmsi());
        saveShipPreparedStatement.setString(2, ship.getImo());
        saveShipPreparedStatement.setString(3, ship.getCallsign());
        saveShipPreparedStatement.setString(4, ship.getName());
        saveShipPreparedStatement.setString(5, ship.getType());
        saveShipPreparedStatement.setString(6, ship.getLength());
        saveShipPreparedStatement.setString(7, ship.getWidth());
        saveShipPreparedStatement.setString(8, ship.getDraft());
        saveShipPreparedStatement.setInt(9, 1200);
        saveShipPreparedStatement.executeUpdate();
        saveShipPreparedStatement.close();
    }

    //Read Ship From DataBase

    public List<Ship> readShipsFromDB(DatabaseConnection databaseConnection) throws SQLException, ParseException {
        Ship ship = null;
        List<Ship> ships = new ArrayList<>();
        Connection connection = databaseConnection.getConnection();
        String sqlCommand = "select * from SHIP";

        PreparedStatement getShipPreparedStatement =
                connection.prepareStatement(sqlCommand);

        try (ResultSet shipResultSet = getShipPreparedStatement.executeQuery()) {

            while(shipResultSet.next()) {
                ship = new Ship(shipResultSet.getString(1), shipResultSet.getString(4), shipResultSet.getString(2), shipResultSet.getString(3), shipResultSet.getString(5), shipResultSet.getString(6), shipResultSet.getString(7),shipResultSet.getString(8));
                for(ShipPosition sp : readShipPositionFromDB(connection, shipResultSet.getString(1))){
                    ship.getSp().insert(sp);
                }
                ships.add(ship);
            }
        }
        getShipPreparedStatement.close();
        return ships;
    }



    //Save Ship Position

    /**
     * Checks is a ShipPosition is already registered on the datase. If the ShipPosition
     * is registered, it does nothing. If it is not, it inserts a new one.
     *
     * @param databaseConnection
     * @param sp
     * @throws SQLException
     */
    private void saveShipPositionToDatabase(DatabaseConnection databaseConnection,
                                    ShipPosition sp) throws SQLException {

        if (!isShipPositionOnDatabase(databaseConnection, sp)) {
            insertShipPositionOnDatabase(databaseConnection, sp);
        }
    }

    /**
     * Checks if a ShipPosition is registered on the Database by its Date and mmsi.
     *
     * @param databaseConnection
     * @param sp
     * @return True if the ShipPosition is registered, False if otherwise.
     * @throws SQLException
     */
    private boolean isShipPositionOnDatabase(DatabaseConnection databaseConnection,
                                     ShipPosition sp) throws SQLException {
        Connection connection = databaseConnection.getConnection();

        boolean isShipPositionOnDatabase = false;

        String sqlCommand = "select * from SHIPPOSITION where ShipMMSI = ? AND Dates = ?";

        PreparedStatement getShipPositionPreparedStatement =
                connection.prepareStatement(sqlCommand);

        getShipPositionPreparedStatement.setString(1, sp.getMmsi());
        getShipPositionPreparedStatement.setTimestamp(2, new Timestamp(sp.getDate().getTime()));

        try (ResultSet shipPositionResultSet = getShipPositionPreparedStatement.executeQuery()) {

            if (shipPositionResultSet.next()) {
                isShipPositionOnDatabase = true;
            } else {
                isShipPositionOnDatabase = false;
            }
        }
        getShipPositionPreparedStatement.close();
        return isShipPositionOnDatabase;
    }

    /**
     * Adds a new ShipPosition record to the database.
     *
     * @param databaseConnection
     * @param sp
     * @throws SQLException
     */
    private void insertShipPositionOnDatabase(DatabaseConnection databaseConnection,
                                      ShipPosition sp) throws SQLException {
        String sqlCommand =
                "INSERT INTO ShipPosition(ShipMMSI,Latitude,Longitude,Dates,Sog,Cog,ShipHeading,Cargo,Transciever) values (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        executeShipPositionStatementOnDatabase(databaseConnection, sp,
                sqlCommand);
    }

    /**
     * Executes the save ShipPosition Statement.
     *
     * @param databaseConnection
     * @param sp
     * @throws SQLException
     */
    private void executeShipPositionStatementOnDatabase(
            DatabaseConnection databaseConnection,
            ShipPosition sp, String sqlCommand) throws SQLException {
        Connection connection = databaseConnection.getConnection();

        PreparedStatement saveShipPositionPreparedStatement =
                connection.prepareStatement(
                        sqlCommand);
        saveShipPositionPreparedStatement.setString(1, sp.getMmsi());
        saveShipPositionPreparedStatement.setString(2, sp.getLat());
        saveShipPositionPreparedStatement.setString(3, sp.getLon());
        saveShipPositionPreparedStatement.setTimestamp(4,new Timestamp(sp.getDate().getTime()));
        saveShipPositionPreparedStatement.setString(5, sp.getSog());
        saveShipPositionPreparedStatement.setString(6, sp.getCog());
        saveShipPositionPreparedStatement.setString(7, sp.getHeading());
        saveShipPositionPreparedStatement.setString(8, sp.getCargo());
        saveShipPositionPreparedStatement.setString(9, sp.getTransciever());
        saveShipPositionPreparedStatement.executeUpdate();
        saveShipPositionPreparedStatement.close();
    }

    //Read Ship Positions from DB

    private List<ShipPosition> readShipPositionFromDB(Connection connection, String shipMMSI) throws SQLException, ParseException {
        ShipPosition sp = null;
        ArrayList<ShipPosition> shipPositions = new ArrayList<>();
        String pattern = "dd/MM/yyyy hh:mm:ss";
        SimpleDateFormat sd = new SimpleDateFormat(pattern);
        String sqlCommand = "select * from SHIPPOSITION WHERE SHIPMMSI = ?";

        PreparedStatement getShipPositionPreparedStatement =
                connection.prepareStatement(sqlCommand);

        getShipPositionPreparedStatement.setString(1, shipMMSI);

        try (ResultSet shipPositionResultSet = getShipPositionPreparedStatement.executeQuery()) {

            while(shipPositionResultSet.next()) {
                sp = new ShipPosition(shipPositionResultSet.getString(1), sd.format(new Date(shipPositionResultSet.getTimestamp(4).getTime())), shipPositionResultSet.getString(2), shipPositionResultSet.getString(3), shipPositionResultSet.getString(5), shipPositionResultSet.getString(6), shipPositionResultSet.getString(7), shipPositionResultSet.getString(8), shipPositionResultSet.getString(9));
                shipPositions.add(sp);
            }
        }
        getShipPositionPreparedStatement.close();

        return shipPositions;
    }

}