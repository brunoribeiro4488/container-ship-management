package lapr.project.data;

import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.OracleTypes;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class ShipAvailable {

    /**
     * Call the function on database that return the ships available
     * @param databaseConnection The database connection
     * @return A string with the necessary information
     * @throws SQLException
     */
    public String getShipsAvailable(DatabaseConnection databaseConnection) throws SQLException {
        Date actualDate = new Date();
        GregorianCalendar date1 = new GregorianCalendar();
        date1.setTime(actualDate);

        while( date1.get( Calendar.DAY_OF_WEEK ) != Calendar.MONDAY ) {
            date1.add(Calendar.DATE, 1);
        }

        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");
        StringBuilder str = new StringBuilder(String.format("Next Monday = %s%n%n", dateFormatter.format(date1.getTime())));
        ResultSet result = null;
        Connection connection = databaseConnection.getConnection();
        String sqlCommand = "{? = call GETSHIPSAVAILABLE}";

        CallableStatement getShipsAvailableStatement =
                connection.prepareCall(sqlCommand);

        getShipsAvailableStatement.registerOutParameter(1, OracleTypes.CURSOR);
        getShipsAvailableStatement.execute();

        result = ((OracleCallableStatement) getShipsAvailableStatement).getCursor(1);

        while(result.next()){
            str.append(String.format("Ship MMSI = %s   Latitude = %s   Longitude = %s%n%n%n", result.getString(1), result.getString(2), result.getString(3)));
        }

        getShipsAvailableStatement.close();
        return str.toString();
    }
}
