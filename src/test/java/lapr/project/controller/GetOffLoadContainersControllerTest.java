package lapr.project.controller;

import lapr.project.data.ConnectionFactory;
import lapr.project.data.DatabaseConnection;
import lapr.project.data.ShipStoreDB;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

class GetOffLoadContainersControllerTest {

    @Test
    void getContainersToOffLoadOnNextPort() throws IOException, SQLException {
        DatabaseConnection databaseConnection = null;
        try {
            databaseConnection = ConnectionFactory.getInstance()
                    .getDatabaseConnection();
        } catch (IOException exception) {
            Logger.getLogger(ShipStoreDB.class.getName())
                    .log(Level.SEVERE, null, exception);
        }
        GetOffLoadContainersController getOffLoadContainersController = new GetOffLoadContainersController();
        boolean expected = true;
        boolean result = getOffLoadContainersController.getContainersToOffLoadOnNextPort(databaseConnection,"784125963");
        Assertions.assertEquals(expected,result);
    }

    @Test
    void getContainersToOffLoadOnNextPortFalse() throws IOException, SQLException {
        DatabaseConnection databaseConnection = null;
        try {
            databaseConnection = ConnectionFactory.getInstance()
                    .getDatabaseConnection();
        } catch (IOException exception) {
            Logger.getLogger(ShipStoreDB.class.getName())
                    .log(Level.SEVERE, null, exception);
        }
        GetOffLoadContainersController getOffLoadContainersController = new GetOffLoadContainersController();
        boolean expected = false;
        boolean result = getOffLoadContainersController.getContainersToOffLoadOnNextPort(databaseConnection,"aaaaaaaaa");
        Assertions.assertEquals(expected,result);
    }
}