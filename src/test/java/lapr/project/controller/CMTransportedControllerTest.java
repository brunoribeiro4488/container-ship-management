package lapr.project.controller;

import lapr.project.data.ConnectionFactory;
import lapr.project.data.DatabaseConnection;
import lapr.project.data.ShipStoreDB;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

class CMTransportedControllerTest {

    /*
    @Test
    void CMTransported() throws SQLException, IOException {
        CMTransportedController cmTransportedController = new CMTransportedController();
        boolean expected = true;
        DatabaseConnection databaseConnection = null;
        try {
            databaseConnection = ConnectionFactory.getInstance()
                    .getDatabaseConnection();
        } catch (IOException exception) {
            Logger.getLogger(ShipStoreDB.class.getName())
                    .log(Level.SEVERE, null, exception);
        }
        boolean result = cmTransportedController.CMTransported(databaseConnection,"2021");
        Assertions.assertEquals(expected,result);
    }
    
     */
}