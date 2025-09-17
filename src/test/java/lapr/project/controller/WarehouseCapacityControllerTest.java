package lapr.project.controller;

import lapr.project.data.WarehouseCapacity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class WarehouseCapacityControllerTest {

    @Test
    void warehouseCapacity() throws SQLException, IOException {
        WarehouseCapacityController c = new WarehouseCapacityController();
        Assertions.assertTrue(c.warehouseCapacity("12333"));
        Assertions.assertNotEquals(false, c.warehouseCapacity("12333"));
    }

    @Test
    void warehouseCapacityfalse() throws SQLException, IOException {
        WarehouseCapacityController c = new WarehouseCapacityController();
        assertFalse(c.warehouseCapacity("12349"));
        Assertions.assertNotEquals(true, c.warehouseCapacity("12349"));
    }

}