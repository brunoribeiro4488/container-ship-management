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

class OccupancyRateMomentControllerTest {

    @Test
    void occupancyRateMoment() throws IOException, SQLException {
        OccupancyRateMomentController occupancyRateMomentController = new OccupancyRateMomentController();
        DatabaseConnection databaseConnection = null;
        try {
            databaseConnection = ConnectionFactory.getInstance()
                    .getDatabaseConnection();
        } catch (IOException exception) {
            Logger.getLogger(ShipStoreDB.class.getName())
                    .log(Level.SEVERE, null, exception);
        }
        boolean expected = true;
        boolean result = occupancyRateMomentController.occupancyRateMoment(databaseConnection,"123456789","2021-02-21 00:02:01");
        Assertions.assertEquals(expected,result);
    }
}