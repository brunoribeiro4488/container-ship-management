package lapr.project.data;

import lapr.project.model.SeaDistance;

import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SeaDistanceStoreDB implements Persistable{

    @Override
    public boolean save(DatabaseConnection databaseConnection, Object object) {
        SeaDistance seaDistance = (SeaDistance) object;
        boolean returnValue = false;

        try {
            saveSeaDistanceToDatabase(databaseConnection,seaDistance);
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


    public void readfromFiletoDB(File file) throws IOException {
        BufferedReader br = null;
        SeaDistanceStoreDB db = new SeaDistanceStoreDB();
        DatabaseConnection databaseConnection = null;
        try {
            databaseConnection = ConnectionFactory.getInstance()
                    .getDatabaseConnection();
        } catch (IOException exception) {
            Logger.getLogger(ShipStoreDB.class.getName())
                    .log(Level.SEVERE, null, exception);
        }
        try{
            br = new BufferedReader(new FileReader(file));

            String line = "";
            String delimiter = ",";
            SeaDistance seaDistance=null;
            String[] tempArr;
            line = br.readLine();
            tempArr = line.split(delimiter);

            while((line = br.readLine()) != null) {
                try {
                    tempArr = line.split(delimiter);
                    seaDistance = new SeaDistance(tempArr[0], tempArr[1], tempArr[2], tempArr[3], tempArr[4], tempArr[5], tempArr[6]);
                    if (!db.isSeaDistanceOnDatabase(databaseConnection, seaDistance)) {
                        db.save(databaseConnection,seaDistance);
                    }
                }catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }catch (IOException e){
            System.out.println("There is no file with that name");
        }finally{
            br.close();
        }
    }

    /**
     * Checks is a ShipPosition is already registered on the datase. If the ShipPosition
     * is registered, it does nothing. If it is not, it inserts a new one.
     *
     * @param databaseConnection
     * @param seaDistance
     * @throws SQLException
     */
    private void saveSeaDistanceToDatabase(DatabaseConnection databaseConnection,
                                           SeaDistance seaDistance) throws SQLException {

        if (!isSeaDistanceOnDatabase(databaseConnection, seaDistance)) {
            insertSeaDistanceOnDatabase(databaseConnection, seaDistance);
        }
    }

    /**
     * Checks if a ShipPosition is registered on the Database by its Date and mmsi.
     *
     * @param databaseConnection
     * @param seaDistance
     * @return True if the ShipPosition is registered, False if otherwise.
     * @throws SQLException
     */
    public boolean isSeaDistanceOnDatabase(DatabaseConnection databaseConnection,
                                           SeaDistance seaDistance) throws SQLException {
        Connection connection = databaseConnection.getConnection();

        boolean isSeaDistanceOnDatabase = false;

        String sqlCommand = "select * from SeaDistance where from_port_id = ? AND to_port_id = ?";

        PreparedStatement getSeaDistancePreparedStatement =
                connection.prepareStatement(sqlCommand);

        getSeaDistancePreparedStatement.setString(1, seaDistance.getFromPortID());
        getSeaDistancePreparedStatement.setString(2,seaDistance.getToPortID());

        try (ResultSet seaDistanceResultSet = getSeaDistancePreparedStatement.executeQuery()) {

            if (seaDistanceResultSet.next()) {
                isSeaDistanceOnDatabase = true;
            } else {
                isSeaDistanceOnDatabase = false;
            }
        }
        getSeaDistancePreparedStatement.close();
        return isSeaDistanceOnDatabase;
    }

    /**
     * Adds a new ShipPosition record to the database.
     *
     * @param databaseConnection
     * @param seaDistance
     * @throws SQLException
     */
    private void insertSeaDistanceOnDatabase(DatabaseConnection databaseConnection,
                                             SeaDistance seaDistance) throws SQLException {
        String sqlCommand =
                "INSERT INTO SeaDistance(from_country,from_port_id,from_port,to_country,to_port_id,to_port,sea_distance) values (?, ?, ?, ?, ?, ?, ?)";
        executeSeaDistanceStatementOnDatabase(databaseConnection, seaDistance,
                sqlCommand);
    }

    /**
     * Executes the save ShipPosition Statement.
     *
     * @param databaseConnection
     * @param seaDistance
     * @throws SQLException
     */
    private void executeSeaDistanceStatementOnDatabase(
            DatabaseConnection databaseConnection,
            SeaDistance seaDistance, String sqlCommand) throws SQLException {
        Connection connection = databaseConnection.getConnection();

        PreparedStatement saveSeaDistancePreparedStatement =
                connection.prepareStatement(
                        sqlCommand);
        saveSeaDistancePreparedStatement.setString(1, seaDistance.getFromCountry());
        saveSeaDistancePreparedStatement.setString(2, seaDistance.getFromPortID());
        saveSeaDistancePreparedStatement.setString(3, seaDistance.getFromPort());
        saveSeaDistancePreparedStatement.setString(4, seaDistance.getToCountry());
        saveSeaDistancePreparedStatement.setString(5, seaDistance.getToPortID());
        saveSeaDistancePreparedStatement.setString(6, seaDistance.getToPort());
        saveSeaDistancePreparedStatement.setString(7, seaDistance.getSeaDistance());
        saveSeaDistancePreparedStatement.executeUpdate();
        saveSeaDistancePreparedStatement.close();
    }

    public ArrayList<SeaDistance> readSeaDistancefromDB(DatabaseConnection databaseConnection) throws SQLException {
        Connection connection = databaseConnection.getConnection();

        String sqlCommand = "select * from SeaDistance";

        PreparedStatement getSeaDistancePreparedStatement =
                connection.prepareStatement(sqlCommand);

        ArrayList<SeaDistance> seaDistanceList = new ArrayList<>();

        try (ResultSet seaDistanceResultSet = getSeaDistancePreparedStatement.executeQuery()) {

            while(seaDistanceResultSet.next()) {
                SeaDistance sd = new SeaDistance(seaDistanceResultSet.getString(1),seaDistanceResultSet.getString(2),seaDistanceResultSet.getString(3),seaDistanceResultSet.getString(4),seaDistanceResultSet.getString(5),seaDistanceResultSet.getString(6),seaDistanceResultSet.getString(7));
                seaDistanceList.add(sd);
            }
        }
        getSeaDistancePreparedStatement.close();
        return seaDistanceList;
    }
}
