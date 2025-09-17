package lapr.project.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class VesselTypeControllerTest {

    @Test
    void getVesselType() throws IOException {
        VesselTypeController c = new VesselTypeController();
        String actual1 = c.getVesselType(4500);
        String actual2 = c.getVesselType(7500);
        String actual3 = c.getVesselType(16000);
        String expected1 = String.format("The best ship for the task is a Panamax.\nThis ship can transport up to 5100 containers.\nThe dimensions of this ship are 294x32x12 (m).\nWith tower in the stern\n");
        String expected2 = String.format("The best ship for the task is a New-Panamax.\nThis ship can transport up to 10000 containers.\nThe dimensions of this ship are 366x49x15 (m).\nWith tower in the middle\n");
        String expected3 = String.format("The best ship for the task is a Ultra Large Container Vessel.\nThis ship has capacity for more than 14500 containers.\nThis ship is bigger than 366x49x15 (m).\nWith tower in the bow\n");

        Assertions.assertEquals(actual1, expected1);
        Assertions.assertNotEquals(actual1, expected2);
        Assertions.assertNotEquals(actual1, expected3);

        Assertions.assertEquals(actual2, expected2);
        Assertions.assertNotEquals(actual2, expected1);
        Assertions.assertNotEquals(actual2, expected3);

        Assertions.assertEquals(actual3, expected3);
        Assertions.assertNotEquals(actual3, expected1);
        Assertions.assertNotEquals(actual3, expected2);
    }

    @Test
    void print() {
    }
}