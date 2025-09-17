package lapr.project.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class WarehouseRateControllerTest {

    @Test
    void warehouseRate() throws SQLException, IOException {
        WarehouseRateController c = new WarehouseRateController();
        Assertions.assertTrue(c.warehouseRate());
        Assertions.assertNotEquals(false, c.warehouseRate());
    }

}