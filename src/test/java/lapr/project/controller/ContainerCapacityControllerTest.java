package lapr.project.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class ContainerCapacityControllerTest {

    @Test
    void containerCapacity() throws SQLException, IOException {
        ContainerCapacityController c = new ContainerCapacityController();
        Assertions.assertTrue(c.containerCapacity());
        Assertions.assertNotEquals(false, c.containerCapacity());
    }
}