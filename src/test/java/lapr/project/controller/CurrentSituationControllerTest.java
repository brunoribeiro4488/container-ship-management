package lapr.project.controller;

import lapr.project.data.ConnectionFactory;
import lapr.project.data.DatabaseConnection;
import lapr.project.data.ShipStoreDB;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

class CurrentSituationControllerTest {

    @Test
    void currentSituation() throws ParseException, SQLException, IOException {
        CurrentSituationController currentSituationController=new CurrentSituationController();
        DatabaseConnection databaseConnection = null;
        try {
            databaseConnection = ConnectionFactory.getInstance()
                    .getDatabaseConnection();
        } catch (IOException exception) {
            Logger.getLogger(ShipStoreDB.class.getName())
                    .log(Level.SEVERE, null, exception);
        }
        boolean expected = true;
        boolean result = currentSituationController.currentSituation(databaseConnection,"12345678910","1234");
        Assertions.assertEquals(expected,result);
    }
}