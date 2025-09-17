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

class OccupancyRateControllerTest {

    @Test
    void occupancyRate() throws IOException, SQLException {
        OccupancyRateController occupancyRateController = new OccupancyRateController();
        DatabaseConnection databaseConnection = null;
        try {
            databaseConnection = ConnectionFactory.getInstance()
                    .getDatabaseConnection();
        } catch (IOException exception) {
            Logger.getLogger(ShipStoreDB.class.getName())
                    .log(Level.SEVERE, null, exception);
        }
        boolean expected = true;
        boolean result = occupancyRateController.occupancyRate(databaseConnection, "123456789","12345");
        Assertions.assertEquals(expected, result);
    }
}