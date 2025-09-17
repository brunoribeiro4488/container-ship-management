package lapr.project.data;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

public class CurrentSituation {

    /**
     * sees the current situation of a container
     *
     * @param databaseConnection
     * @param ncontainer
     * @param ccontainer
     * @return
     * @throws SQLException
     */
    public String currentSituation(DatabaseConnection databaseConnection,String ncontainer,String ccontainer) throws SQLException {

        Connection connection = databaseConnection.getConnection();

        String command = "call Container_Situation(?, ?, ?, ?)";
        CallableStatement cstmt = connection.prepareCall(command);
        cstmt.setString(1, ncontainer);
        cstmt.setString(2, ccontainer);
        cstmt.registerOutParameter(3, Types.VARCHAR);
        cstmt.registerOutParameter(4, Types.VARCHAR);
        cstmt.execute();
        String transporttype=cstmt.getString(3);
        String transportname=cstmt.getString(4);
        cstmt.close();
        return String.format("Type of location: %s%nName: %s",transporttype,transportname);
    }
}
