package lapr.project.controller;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class ShipVoyagesControllerTest {

    @Test
    void shipVoyages() throws SQLException, IOException {
        ShipVoyagesController svc = new ShipVoyagesController();
        boolean result=svc.shipVoyages("789789789");
        assertTrue(result);
    }

    @Test
    void print() {
    }
}