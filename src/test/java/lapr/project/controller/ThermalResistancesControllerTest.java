package lapr.project.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class ThermalResistancesControllerTest {

    @Test
    void getThermalResistances() throws IOException {
        ThermalResistancesController thermalResistancesController = new ThermalResistancesController();
        String expected = "The container that operates at temperatures of 7ºC has as thermal resistance = 4.59K/W by square meter. This container is made of: Outer walls are Stainless steel, middle layers are Polyurethane foam and interior walls are Soft-board wood. \n The container that operates at temperatures of -5ºC has as thermal resistance = 7.00K/W by square meter. This container is made of: Outer walls are Stainless steel, middle layers are Glass wool and interior walls are Expanded cork";
        String result = thermalResistancesController.getThermalResistances();
        Assertions.assertEquals(expected,result);
    }
}