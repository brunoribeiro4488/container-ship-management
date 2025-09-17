package lapr.project.controller;

import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class ShipOccupiedControllerTest {

    @Test
    void insertCargoManifestForShip() throws SQLException, IOException {
        ShipOccupiedController c = new ShipOccupiedController();
        assertTrue(c.insertCargoManifestForShip("11113", "1", "2021-02-21 00:07:01", "0", "12345"));
        assertFalse(c.insertCargoManifestForShip("11111", "2", "2021-02-21 00:10:01", "0", "12345"));
        assertNotEquals(false, c.insertCargoManifestForShip("11113", "1", "2021-02-21 00:07:01", "0", "12345"));
        assertNotEquals(true, c.insertCargoManifestForShip("11111", "2", "2021-02-21 00:10:01", "0", "12345"));
    }

    @Test
    void print() {
    }
}