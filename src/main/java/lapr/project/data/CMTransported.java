package lapr.project.data;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

public class CMTransported {

    /**
     * calculates the average of containers in a year
     *
     * @param databaseConnection
     * @param year
     * @return
     * @throws SQLException
     */
    public int avgContainers(DatabaseConnection databaseConnection,String year) throws SQLException {

        Connection connection = databaseConnection.getConnection();

        String command = "call  avgcontainers(?, ?)";
        CallableStatement cstmt = connection.prepareCall(command);
        cstmt.setInt(1, Integer.parseInt(year));
        cstmt.registerOutParameter(2, Types.INTEGER);
        cstmt.execute();
        int average=cstmt.getInt(2);
        cstmt.close();
        return average;
    }

    /**
     * sees how many cargo manifests were transported in a year
     *
     * @param databaseConnection
     * @param year
     * @return
     * @throws SQLException
     */
    public int manifestInYear(DatabaseConnection databaseConnection,String year) throws SQLException {

        Connection connection = databaseConnection.getConnection();

        String command = "call  manifestinyear(?, ?)";
        CallableStatement cstmt = connection.prepareCall(command);
        cstmt.setInt(1, Integer.parseInt(year));
        cstmt.registerOutParameter(2, Types.INTEGER);
        cstmt.execute();
        int cont=cstmt.getInt(2);
        cstmt.close();
        return cont;
    }

}
