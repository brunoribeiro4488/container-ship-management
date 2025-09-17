package lapr.project.data;

import lapr.project.model.Country;

import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CountryStoreDB implements Persistable{

    @Override
    public boolean save(DatabaseConnection databaseConnection, Object object) {
        Country country = (Country) object;
        boolean returnValue = false;

        try {
            saveCountryToDatabase(databaseConnection,country);
            returnValue = true;

        } catch (SQLException ex) {
            Logger.getLogger(PortStoreDB.class.getName())
                    .log(Level.SEVERE, null, ex);
            databaseConnection.registerError(ex);
            returnValue = false;
        }
        return returnValue;
    }

    public void readCountryFromFiletoDB(File file) throws IOException {
        BufferedReader br = null;
        CountryStoreDB db = new CountryStoreDB();
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
            Country country=null;
            String[] tempArr;
            line = br.readLine();
            tempArr = line.split(delimiter);

            while((line = br.readLine()) != null) {
                try {
                    tempArr = line.split(delimiter);
                    country = new Country(tempArr[0], tempArr[1], tempArr[2], tempArr[3], tempArr[4], tempArr[5], tempArr[6],tempArr[7]);
                    if (!db.isCountryOnDatabase(databaseConnection, country)) {
                        db.save(databaseConnection,country);
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


    private void saveCountryToDatabase(DatabaseConnection databaseConnection,
                                       Country country) throws SQLException {

        if (!isCountryOnDatabase(databaseConnection, country)) {
            insertCountryOnDatabase(databaseConnection, country);
        }
    }


    public boolean isCountryOnDatabase(DatabaseConnection databaseConnection,
                                        Country country) throws SQLException {
        Connection connection = databaseConnection.getConnection();

        boolean isCountryOnDatabase = false;

        String sqlCommand = "select * from Country where Country = ?";

        PreparedStatement getPortPreparedStatement =
                connection.prepareStatement(sqlCommand);

        getPortPreparedStatement.setString(1, country.getCountry());

        try (ResultSet portResultSet = getPortPreparedStatement.executeQuery()) {

            if (portResultSet.next()) {
                isCountryOnDatabase = true;
            } else {
                isCountryOnDatabase = false;
            }
        }
        getPortPreparedStatement.close();
        return isCountryOnDatabase;
    }


    private void insertCountryOnDatabase(DatabaseConnection databaseConnection,
                                         Country country) throws SQLException {
        String sqlCommand =
                "INSERT INTO Country(Continent,Alpha2_Code,Alpha3_Code,Country,Population,Capital,Latitude,Longitude) values (?, ?, ?, ?, ?, ?, ?, ?)";
        executeCountryStatementOnDatabase(databaseConnection, country,
                sqlCommand);
    }


    private void executeCountryStatementOnDatabase(
            DatabaseConnection databaseConnection,
            Country country, String sqlCommand) throws SQLException {
        Connection connection = databaseConnection.getConnection();

        PreparedStatement savePortPreparedStatement =
                connection.prepareStatement(
                        sqlCommand);
        savePortPreparedStatement.setString(1, country.getContinent());
        savePortPreparedStatement.setString(2, country.getAlpha2Code());
        savePortPreparedStatement.setString(3, country.getAlpha3Code());
        savePortPreparedStatement.setString(4, country.getCountry());
        savePortPreparedStatement.setString(5, country.getPopulation());
        savePortPreparedStatement.setString(6, country.getCapital());
        savePortPreparedStatement.setString(7, country.getLatitude());
        savePortPreparedStatement.setString(8, country.getLongitude());
        savePortPreparedStatement.executeUpdate();
        savePortPreparedStatement.close();
    }

    public ArrayList<Country> readCountryfromDB(DatabaseConnection databaseConnection) throws SQLException {
        Country country=null;
        Connection connection = databaseConnection.getConnection();

        String sqlCommand = "select * from Country";

        PreparedStatement getCountryPreparedStatement =
                connection.prepareStatement(sqlCommand);

        ArrayList<Country> countryList = new ArrayList<>();

        try (ResultSet countryResultSet = getCountryPreparedStatement.executeQuery()) {

            while (countryResultSet.next()) {
                        country = new Country(countryResultSet.getString(1), countryResultSet.getString(2), countryResultSet.getString(3), countryResultSet.getString(4), countryResultSet.getString(5),countryResultSet.getString(6),countryResultSet.getString(7),countryResultSet.getString(8));
                        countryList.add(country);
            }
        }
        getCountryPreparedStatement.close();
        return countryList;
    }
}
