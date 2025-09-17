package lapr.project.controller;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class OccupationmapPortControllerTest {

    @Test
    void occupationmapPort() throws SQLException, IOException {
        OccupationmapPortController opc = new OccupationmapPortController();
        boolean result = opc.occupationmapPort("78978","2021-03-01 00:00:00");
        assertTrue(result);
    }
}