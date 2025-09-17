package lapr.project.controller;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class FreightNetworkControllerTest {

    @Test
    void freightNetwork() throws SQLException, IOException {
        FreightNetworkController freightNetworkController = new FreightNetworkController();
        boolean result=freightNetworkController.freightNetwork(3);
        assertTrue(result);
    }
}