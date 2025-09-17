package lapr.project.data;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ContainerSituationWarming {


    /**
     * Get the situation of a container
     * @param number The container number
     * @param isoCode The container ISO code
     * @param clientReg The client registration code
     * @return The situation of the container or error if input is invalid.
     * @throws SQLException
     */
    public String getContainerSituationWarming(String number, String isoCode, String clientReg) throws SQLException {
        DatabaseConnection databaseConnection = null;
        try {
            databaseConnection = ConnectionFactory.getInstance()
                    .getDatabaseConnection();
        } catch (IOException exception) {
            Logger.getLogger(ShipStoreDB.class.getName())
                    .log(Level.SEVERE, null, exception);
        }

        Connection connection = databaseConnection.getConnection();

        String command = "call Container_SituationWarming(?, ?, ?, ?, ?)";
        CallableStatement cstmt = connection.prepareCall(command);
        cstmt.setString(1, number);
        cstmt.setString(2, clientReg);
        cstmt.setString(3, isoCode);
        cstmt.registerOutParameter(4, Types.VARCHAR);
        cstmt.registerOutParameter(5, Types.VARCHAR);
        try {
            cstmt.execute();
            String transporttype = cstmt.getString(4);
            String transportname = cstmt.getString(5);
            return String.format("Type of location: %s%nName: %s",transporttype,transportname);
        }catch(SQLException e){
            if(e.getErrorCode() == 20011) {
                return "Error Code 11 – Container is not leased by client";
            }else{
                return "Error Code 10 - Invalid Container ID";
            }
        }
    }
}
