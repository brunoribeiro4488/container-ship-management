package lapr.project.controller;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class ColourMapControllerTest {

    @Test
    void colourMap() throws SQLException, IOException {
        ColourMapController colourMapController = new ColourMapController();
        boolean result=colourMapController.colourMap();
        assertTrue(result);
    }
}