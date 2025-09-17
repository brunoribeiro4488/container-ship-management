package lapr.project.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class EnergyWithSidesControllerTest {

    @Test
    void getEnergy() throws IOException {
        EnergyWithSidesController energyWithSidesController = new EnergyWithSidesController();
        String result = energyWithSidesController.getEnergy(6,6,3600,20);
        String expected = "The container that operates at temperatures of 7ºC in a trip of 1 hours and 0 minutes with an exterior temperature of 20 ºC" +
                " needs 1258214.51 J to maintain it's temperature\n" +
                "The container that operates at temperatures of -5ºC in a trip of 1 hours and 0 minutes with an exterior temperature of 20 ºC" +
                " needs 1586357.02 J to maintain it's temperature";
        Assertions.assertEquals(expected,result);
    }

    @Test
    void getgenerators() throws IOException {
        EnergyWithSidesController energyWithSidesController = new EnergyWithSidesController();
        String result = energyWithSidesController.getgenerators(10,2,20,10,12,20,20);
        String expected = "To maintain the temperature of 10 containers of -5ºC and 10 containers of 7ºC arranged in a specific way, 1 generators of 75KW are needed";
        Assertions.assertEquals(expected,result);
    }
}