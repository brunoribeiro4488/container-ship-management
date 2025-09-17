package lapr.project.data;

import lapr.project.model.Border;

import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BorderStoreDB implements Persistable{

    @Override
    public boolean save(DatabaseConnection databaseConnection, Object object) {
        Border border = (Border) object;
        boolean returnValue = false;

        try {
            saveBorderToDatabase(databaseConnection,border);
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

    public void readBordersFromFileToDB(File file) throws IOException {
        BufferedReader br = null;
        BorderStoreDB db = new BorderStoreDB();
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
            Border border=null;
            String[] tempArr;
            line = br.readLine();
            tempArr = line.split(delimiter);

            while((line = br.readLine()) != null) {
                try {
                    tempArr = line.split(delimiter);
                    border = new Border(tempArr[0], tempArr[1]);
                    if (!db.isBorderOnDatabase(databaseConnection, border)) {
                        db.save(databaseConnection,border);
                    }
                }catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }catch (FileNotFoundException e){
            System.out.println("There is no file with that name");
        } catch (IOException e) {
            e.printStackTrace();
        } finally{
            br.close();
        }
    }

    /**
     * Checks is a ShipPosition is already registered on the datase. If the ShipPosition
     * is registered, it does nothing. If it is not, it inserts a new one.
     *
     * @param databaseConnection
     * @param border
     * @throws SQLException
     */
    private void saveBorderToDatabase(DatabaseConnection databaseConnection,
                                      Border border) throws SQLException {

        if (!isBorderOnDatabase(databaseConnection, border)) {
            insertBorderOnDatabase(databaseConnection, border);
        }
    }

    /**
     * Checks if a ShipPosition is registered on the Database by its Date and mmsi.
     *
     * @param databaseConnection
     * @param border
     * @return True if the ShipPosition is registered, False if otherwise.
     * @throws SQLException
     */
    public boolean isBorderOnDatabase(DatabaseConnection databaseConnection,
                                      Border border) throws SQLException {
        Connection connection = databaseConnection.getConnection();

        boolean isBorderOnDatabase = false;

        String sqlCommand = "select * from Border where country1 = ? AND country2 = ?";

        PreparedStatement getBorderPreparedStatement =
                connection.prepareStatement(sqlCommand);

        getBorderPreparedStatement.setString(1, border.getCountry1());
        getBorderPreparedStatement.setString(2, border.getCountry2());

        try (ResultSet borderResultSet = getBorderPreparedStatement.executeQuery()) {

            if (borderResultSet.next()) {
                isBorderOnDatabase = true;
            } else {
                isBorderOnDatabase = false;
            }
        }
        getBorderPreparedStatement.close();
        return isBorderOnDatabase;
    }

    /**
     * Adds a new ShipPosition record to the database.
     *
     * @param databaseConnection
     * @param border
     * @throws SQLException
     */
    private void insertBorderOnDatabase(DatabaseConnection databaseConnection,
                                        Border border) throws SQLException {
        String sqlCommand =
                "INSERT INTO Border(country1,country2) values (?, ?)";
        executeBorderStatementOnDatabase(databaseConnection, border,
                sqlCommand);
    }

    /**
     * Executes the save ShipPosition Statement.
     *
     * @param databaseConnection
     * @param border
     * @throws SQLException
     */
    private void executeBorderStatementOnDatabase(
            DatabaseConnection databaseConnection,
            Border border, String sqlCommand) throws SQLException {
        Connection connection = databaseConnection.getConnection();

        PreparedStatement saveBorderPreparedStatement =
                connection.prepareStatement(
                        sqlCommand);
        saveBorderPreparedStatement.setString(1, border.getCountry1());
        saveBorderPreparedStatement.setString(2, border.getCountry2());
        saveBorderPreparedStatement.executeUpdate();
        saveBorderPreparedStatement.close();
    }

    public ArrayList<Border> readBorderfromDB(DatabaseConnection databaseConnection) throws SQLException {
        Border border=null;
        Connection connection = databaseConnection.getConnection();

        String sqlCommand = "select * from Border";

        PreparedStatement getBorderPreparedStatement =
                connection.prepareStatement(sqlCommand);

        ArrayList<Border> borderList = new ArrayList<>();

        try (ResultSet borderResultSet = getBorderPreparedStatement.executeQuery()) {

            while(borderResultSet.next()) {
                border = new Border(borderResultSet.getString(1),borderResultSet.getString(2).substring(1));
                borderList.add(border);
            }
        }
        getBorderPreparedStatement.close();
        return borderList;
    }
}
