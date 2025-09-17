package lapr.project.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NegativeContainerTest {

    @Test
    void getThermicalResistance() {
        NegativeContainer negativeContainer = new NegativeContainer();
        double expected=7.001048689138577;
        double result = negativeContainer.getThermicalResistance();
        Assertions.assertEquals(expected,result);
    }

    @Test
    void getOuterWalls() {
        NegativeContainer negativeContainer=new NegativeContainer();
        String expected = "Stainless steel";
        String result = negativeContainer.getOuterWalls();
        Assertions.assertEquals(expected,result);
    }

    @Test
    void getInteriorWalls() {
        NegativeContainer negativeContainer=new NegativeContainer();
        String expected = "Expanded cork";
        String result = negativeContainer.getInteriorWalls();
        Assertions.assertEquals(expected,result);
    }

    @Test
    void getMiddleLayers() {
        NegativeContainer negativeContainer=new NegativeContainer();
        String expected = "Glass wool";
        String result = negativeContainer.getMiddleLayers();
        Assertions.assertEquals(expected,result);
    }
}