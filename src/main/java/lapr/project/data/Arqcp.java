package lapr.project.data;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

public class Arqcp {
    /**
     * Call the function on database that return the position and id for a container of a given manifest
     * @param databaseConnection The database connection
     * @return The information
     * @throws SQLException
     */
    public String arqcp(DatabaseConnection databaseConnection,String cargomanifestId) throws SQLException {
        String positions = "";
        Connection connection = databaseConnection.getConnection();
        String sqlCommand = "{call arqcp(?,?)}";

        CallableStatement arqcpStatement =
                connection.prepareCall(sqlCommand);

        arqcpStatement.setString(1,cargomanifestId);
        arqcpStatement.registerOutParameter(2, Types.VARCHAR);

        arqcpStatement.execute();

        positions=arqcpStatement.getString(2);

        arqcpStatement.close();

        StringBuilder result= new StringBuilder();

        Scanner scanner = new Scanner(positions);
        int x=0;
        int y=0;
        int z=0;
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if(String.valueOf(line.charAt(0)).equals("(")){
                line=line.replaceAll("[()]", "");
                if (x<Integer.parseInt(String.valueOf(line.charAt(0)))){
                    x=Integer.parseInt(String.valueOf(line.charAt(0)));
                }
                if (y<Integer.parseInt(String.valueOf(line.charAt(2)))){
                    y=Integer.parseInt(String.valueOf(line.charAt(2)));
                }
                if (z<Integer.parseInt(String.valueOf(line.charAt(4)))){
                    z = Integer.parseInt(String.valueOf(line.charAt(4)));

                }

                result.append(line.replaceAll("[,]", "\n"));
            }else{
                result.append("\n").append(line).append("\n");
            }
        }

        result.insert(0, (x + 1) + "\n" + (y + 1) + "\n" + (z + 1) + "\n");
        scanner.close();
        result = new StringBuilder(result.toString().replaceAll("^[\n\r]", "").replaceAll("[\n\r]$", ""));
        return result.toString();
    }
}
