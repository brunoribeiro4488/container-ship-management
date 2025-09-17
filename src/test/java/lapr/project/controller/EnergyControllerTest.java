package lapr.project.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class EnergyControllerTest {

    @Test
    void getEnergy() throws IOException {
        EnergyController energyController=new EnergyController();
        String result = energyController.getEnergy(9000, 20);
        String expected = "The container that operates at temperatures of 7ºC in a trip of 2 hours and 30 minutes with an exterior temperature of 20 ºC" +
                " needs 1794253.27 J to maintain it's temperature\n" +
                "The container that operates at temperatures of -5ºC in a trip of 2 hours and 30 minutes with an exterior temperature of 20 ºC" +
                " needs 2262194.75 J to maintain it's temperature";
        Assertions.assertEquals(expected,result);
    }
}